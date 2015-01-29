/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeart;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nathalie
 */
@WebServlet(name = "AfficherPanierServlet", urlPatterns={"/AfficherPanierServlet"})
public class AfficherPanierServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        PrintWriter out = response.getWriter();
        String listePanier = "";
        String[] imgDansPanier;
        List<Creation> maListeImage = new ArrayList<Creation>();
        List<Creation> resCrea = freeart.EJBCreation.getCreations();
        Cookie[] cookies = request.getCookies();

        if (cookies != null)
        {
            for (Cookie tousLesCookies : cookies)
            {
                if (tousLesCookies.getName().equals("listeImg"))
                {
                    listePanier = tousLesCookies.getValue();
                }
            }
        }

        imgDansPanier = listePanier.split(":");

        for (Creation toutesLesCreations : resCrea)
        {
            for (String lienImg : imgDansPanier)
            {
                if (toutesLesCreations.getFile().equals(lienImg))
                {
                    maListeImage.add(toutesLesCreations);
                }
            }
        }

        String UPLOAD_DIRECTORY = this.getClass().getResource('/' + this.getClass().getName().replace('.', '/') + ".class").toString().substring(6, 97) + "web/";

        List<FileInputStream> allin = new ArrayList<FileInputStream>();

        ZipOutputStream outp = new ZipOutputStream(new FileOutputStream(UPLOAD_DIRECTORY + "img/tempZip.zip"));

        for (Creation crea : maListeImage)
        {
            allin.add(new FileInputStream(UPLOAD_DIRECTORY + crea.getFile()));
            outp.putNextEntry(new ZipEntry(crea.getFile()));
            byte[] b = new byte[1024];
            int count;

            for (FileInputStream in : allin)
            {
                while ((count = in.read(b)) > 0)
                {
                    outp.write(b, 0, count);
                }
            }
        }

        outp.close();
        in.close();

        request.setAttribute("monPanier", maListeImage);

        RequestDispatcher rd = request
                .getRequestDispatcher("/afficherPanier.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
