package dk.zenlike.wineprices.servlets;

import dk.zenlike.wineprices.model.SourceConfiguration;
import dk.zenlike.wineprices.persistence.SourceConfigService;
import dk.zenlike.wineprices.persistence.WinePriceService;
import dk.zenlike.wineprices.persistence.impl.SourceConfigServiceImpl;
import dk.zenlike.wineprices.persistence.impl.WinePriceServiceImpl;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PriceCheck", value = "/tasks/checkprices")
public class PriceCheckServlet extends HttpServlet {

    final SourceConfigService sourceConfigService = new SourceConfigServiceImpl();
    final WinePriceService winePriceService = new WinePriceServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        for (SourceConfiguration srcConfig: sourceConfigService.getSourceConfigurations()) {

        }
    }

}
