package dk.zenlike.wineprices.servlets;

import dk.zenlike.wineprices.model.SourceConfiguration;
import dk.zenlike.wineprices.model.WinePrice;
import dk.zenlike.wineprices.persistence.SourceConfigService;
import dk.zenlike.wineprices.persistence.WinePriceService;
import dk.zenlike.wineprices.persistence.impl.SourceConfigServiceImpl;
import dk.zenlike.wineprices.persistence.impl.WinePriceServiceImpl;
import dk.zenlike.wineprices.sources.service.FetcherService;
import dk.zenlike.wineprices.sources.service.impl.FetcherServiceImpl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "PriceCheck", value = "/tasks/checkprices")
public class PriceCheckServlet extends HttpServlet {

    private static SourceConfigService sourceConfigService = new SourceConfigServiceImpl();
    private static FetcherService fetcherService = new FetcherServiceImpl();
    private static WinePriceService winePriceService = new WinePriceServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        for (SourceConfiguration sourceConfig: sourceConfigService.getSourceConfigurations()) {
            List<WinePrice> winePrices = winePriceService.getWinePricesBySource(sourceConfig.getSourceName());

            for (WinePrice currWinePrice: winePrices) {
                fetcherService.updateWinePrice(currWinePrice, sourceConfig);

                if (currWinePrice.getName().contains("2012 Suolo Tenuta di Argiano")) {
                    String priceStr = currWinePrice.getPrice();

                    if (priceStr.contains(",")) {
                        priceStr = priceStr.split(",")[0];

                    }

                    int price = Integer.parseInt(priceStr);

                    if (price < 1200) {
                        sendMailNotification(currWinePrice.getName(), sourceConfig.getSourceName(), 1200);
                    }
                }
            }
        }
    }

    private void sendMailNotification(String wineName, String source, int limit) {
        Properties prop = new Properties();
        Session session = Session.getDefaultInstance(prop,null);

        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("runenielsen@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("runenielsen@gmail.com", "Rune Nielsen"));
            msg.setSubject("Wine notification");
            msg.setText("The wine '" + wineName + "' has dropped below " + limit + " kr. at " + source + "!");

            Transport.send(msg);
        }
        catch (AddressException e) {
            e.printStackTrace();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
