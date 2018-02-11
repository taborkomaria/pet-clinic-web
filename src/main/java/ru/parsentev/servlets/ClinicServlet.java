package ru.parsentev.servlets;

import pet.Animal;
import pet.Dog;
import pet.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClinicServlet extends HttpServlet {

    /**
     * Array of pets
     */
    final List<Pet> pets = new CopyOnWriteArrayList<Pet>();

    /**
     * doGet
     * @param req Request
     * @param resp Response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         Name : <input type='text' name='name'>"+
                        "         <input type='submit' value='Submit'>"+
                        "     <form>"+
                        this.viewPets() +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    /**
     * do Post
     * @param req Request
     * @param resp Response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.pets.add(new Dog(new Animal(req.getParameter("name"))));
        doGet(req, resp);
    }

    /**
     * Create list of pets
     * @return table of pets
     */
    private String viewPets() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Pets</p>");
        sb.append("<table style='border : 1px solid black'>");

        /**
         * Add all pets to table
         */
        pets.forEach(pet->
            sb.append("<tr><td style='border : 1px solid black'>").append(pet.getName()).append("</td></tr>")
        );


        sb.append("</table>");
        return sb.toString();
    }
}
