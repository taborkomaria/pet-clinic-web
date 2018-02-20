package ru.petclinic.servlets.servlets;

import pet.Animal;
import pet.Dog;
import pet.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

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
                        "         Pet's name: <input type='text' name='name'>"+
                        "         <input type='submit' name='add' value='Add Pet'>"+
                        "     <form>"+
                        "<br><br>"+
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         Delete pet by name: <input type='text' name='deleteName'>"+
                        "         <input type='submit' name='delete' value='Delete Pet'>"+
                        "     <form>"+
                        this.viewPets(pets) +
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         Search pet by name: <input type='text' name='searchName'>"+
                        "         <input type='submit' name='search' value='Search'>"+
                        "     <form>"+
                        "<br>"+
                        this.viewSearchResult(req)+
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

        /**
         * Add new name to pets
         */
        if(req.getParameter("add")!= null){
            this.pets.add(new Dog(new Animal(req.getParameter("name"))));
        }
        /**
         * Delete pet by name
         */
        if(req.getParameter("delete")!= null){
            String deleteName = req.getParameter("deleteName");
            pets.removeIf(pet -> deleteName.equals(pet.getName()));

        }
        doGet(req, resp);
    }

    /**
     * Create list of all pets
     * @return string
     */
    private String viewPets(List<Pet> petsList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Pets:</p>");
        sb.append("<ul>");

        /**
         * Add all pets to list
         */
        petsList.forEach(pet->
            sb.append("<li>").append(pet.getName()).append("</li>")
        );


        sb.append("</ul>");
        return sb.toString();
    }


    private String viewSearchResult(HttpServletRequest req) {

        if(req.getParameter("search")!= null){
            String searchName = req.getParameter("searchName");

            /**
             * Search pet by name using stream
             */
            List<Pet> result = pets.stream()
                   // .filter(pet -> searchName.equals(pet.getName())) //Search only equals name
                    .filter(pet -> pet.getName().toLowerCase().contains( searchName.toLowerCase())) //Search for subname
                    .collect(Collectors.toList());
            /**
             * Result would be empty if there is no matching name in pets
             */
            if (result.isEmpty()) {
                return "No pets found";
            }
            return viewPets(result);
        }
        return "";
    }

}
