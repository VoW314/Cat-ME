/*
 * Created by Shreyas Bera on 2026.3.19
 * Copyright © 2026 Osman Balci. All rights reserved
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Cat;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.CatFacade;
import edu.vt.globals.Methods;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

@Named("bookController")
@SessionScoped
public class BookController implements Serializable {

    private List<Book> listOfBooks = null;
    private Book selected;
    private Date bookPublicationDate;


    // Used the following snippet and adapted it to this problem:
    // https://manta.cs.vt.edu/cs3754/StudentsOnly/CodeSnippets/Database/DatabaseSearch.html

    // Page Count Query
    private Integer minPagesQ;
    private Integer maxPagesQ;


    //Date Queries
    private Date date1Q;
    private Date date2Q;


    // Search types 1 thorugh 7
    private Integer searchType;

    //Type 1 searchs
    private String searchField;
    private String searchString;


    @EJB
    private BookFacade bookFacade;

    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    BookFacade bean into the instance variable 'bookFacade' after it is instantiated at runtime.
     */

    // Inject API controller to access search results
    @Inject
    private BookApiController booksApiController;



    /*
    =========================
    Getter and Setter Methods
    =========================
     */


    public Book getSelected() {
        return selected;
    }

    public void setSelected(Book selected) {
        this.selected = selected;
    }

    // This getter method is called in Create.xhtml and Edit.xhtml
    public Date getBookPublicationDate() {
        if (selected != null && selected.getPublishedDate() != null) {

            // Convert date from SQL type to Java type
            bookPublicationDate = new Date(selected.getPublishedDate().getTime());
            return bookPublicationDate;
        }
        // Return today's date
        return new Date();
    }

    // This setter method is called in Create.xhtml and Edit.xhtml
    public void setBookPublicationDate(Date bookPublicationDate) {
        this.bookPublicationDate = bookPublicationDate;
        if (selected != null) {
            // date type java to SQL date type
            java.sql.Date dateSqlType = new java.sql.Date(bookPublicationDate.getTime());

            // Set selected Book's published date in SQL type
            selected.setPublishedDate(dateSqlType);
        }
    }

    // More date query setters and getters
    public Date getDate1Q() {
        return date1Q;
    }
    public Date getDate2Q() {
        return date2Q;
    }

    public void setDate1Q(Date date1Q) {
        this.date1Q = date1Q;
    }
    public void setDate2Q(Date date2Q) {
        this.date2Q = date2Q;

    }

    public Integer getMinPagesQ() {
        return minPagesQ;
    }
    public void setMinPagesQ(Integer minPagesQ) {
        this.minPagesQ = minPagesQ;
    }

    public Integer getMaxPagesQ() {
        return maxPagesQ;
    }
    public void setMaxPagesQ(Integer maxPagesQ) {
        this.maxPagesQ = maxPagesQ;
    }

    // Instance methods

    // unselect the book object by setting our slection to null
    public void unselect() {
        selected = null;
    }

    // cancel
    public String cancel() {
        // Unselect previously selected book object if any
        selected = null;
        return "/book/List?faces-redirect=true";
    }

    // This is the Getter method for the instance variable searchItems
    // This comes from the code snippets link above
    public List<Book> getListOfBooks() {

        if (listOfBooks == null) {

            // Make sure null is never returned for searchType
            if(searchType == null){
                listOfBooks = bookFacade.findAll();
                listOfBooks.sort(Comparator.comparing(Book::getTitle));
                return listOfBooks;
            }

            switch (searchType) {
                // text search
                case 1:
                    switch (searchField) {

                        case "Title":
                            listOfBooks = bookFacade.titleQuery(searchString);
                            break;

                        case "Author":
                            listOfBooks = bookFacade.authorQuery(searchString);
                            break;

                        case "Publisher":
                            listOfBooks = bookFacade.publisherQuery(searchString);
                            break;

                        case "Description":
                            listOfBooks = bookFacade.descriptionQuery(searchString);
                            break;

                        default:
                            listOfBooks = bookFacade.allQuery(searchString);
                    }
                    break;

                // date searches 2-4
                case 2:
                    listOfBooks = bookFacade.dateGreaterEqualQuery(date1Q);
                    break;

                case 3:
                    listOfBooks = bookFacade.dateLessEqualQuery(date1Q);
                    break;

                case 4:
                    listOfBooks = bookFacade.dateBetweenQuery(date1Q, date2Q);
                    break;

                // page count searches 5-7
                case 5:
                    listOfBooks = bookFacade.pagesGreaterEqualQuery(minPagesQ);
                    break;

                case 6:
                    listOfBooks = bookFacade.pagesLessEqualQuery(maxPagesQ);
                    break;

                case 7:
                    listOfBooks = bookFacade.pagesBetweenQuery(minPagesQ, maxPagesQ);
                    break;

                default: //defaults
                    listOfBooks = bookFacade.findAll();
            }

            //make sure never null. Just so something exists
            if(listOfBooks == null){
                listOfBooks = new ArrayList<>();
            }


        }

        return listOfBooks;
    }

    //helps create a new book
    public void prepareCreate() {
        /*
        Instantiate a new Book object and store its object reference into
        instance variable 'selected'. The Book class is defined in Book.java
         */
        selected = new Book();
    }

    //create a new book
    public void create() {
        Methods.preserveMessages();

        persist(PersistAction.CREATE,"Book was Successfully Created!");

        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The CREATE operation is successfully performed.
            selected = null;
            listOfBooks = null;
        }
    }


    // update a selected book
    public void update() {
        Methods.preserveMessages();

        persist(PersistAction.UPDATE,"Book was Successfully Updated!");

        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            listOfBooks = null;
        }
    }

    // Realized that I forgot to add an actual way to get search strings
    // and do stuff with them

    // search String methods
    public String getSearchString(){
        return searchString;
    }

    public void setSearchString(String searchString){
        this.searchString = searchString;
    }

    // same with search fields
    public String getSearchField(){
        return searchField;
    }

    public void setSearchField(String searchField){
        this.searchField = searchField;
    }

    public Integer getSearchType(){
        return searchType;
    }
    public void setSearchType(Integer searchType){
        this.searchType = searchType;
    }




    //delete selected book from database
    public void destroy() {
        Methods.preserveMessages();

        persist(PersistAction.DELETE,"Book was Successfully Deleted!");

        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            listOfBooks = null;
        }
    }


    /*
     ******************************************
     *   Display the Search Results JSF Page  *
     ******************************************
     */
    public String search(Integer type) {
        // Set search type given as input parameter
        searchType = type;

        // Unselect previously selected book if any before showing the search results
        selected = null;

        // Invalidate list of search items to trigger re-query.
        listOfBooks = null;

        return "/databaseSearch/DatabaseSearchResults?faces-redirect=true";
    }

    // Realized I had renamed getSearchResults everywhere but some places
    // Thought this is easier for now
    public List<Book> getSearchResults() {
        return getListOfBooks();
    }





    /*
     **********************************************************************************************
     *   Perform CREATE, UPDATE (EDIT), and DELETE (DESTROY, REMOVE) Operations in the Database
     **********************************************************************************************
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    bookFacade.edit(selected);
                } else {
                    bookFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (!msg.isEmpty()) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
            }
        }
    }

    // made getters for the buttons too after getting an error about bad api search returns
    public String searchType1(){
        return search(1);
    }
    public String searchType2() {
        return search(2);
    }
    public String searchType3() {
        return search(3);
    }
    public String searchType4() {
        return search(4);
    }
    public String searchType5() {
        return search(5);
    }
    public String searchType6() {
        return search(6);
    }
    public String searchType7() {
        return search(7);
    }


    //Add book selected from API search REsult list to fav list
    public String addToFavorites() throws ParseException {

        selected = new Book();

        selected.setTitle(booksApiController.getSelected().getTitle());
        selected.setAuthors(booksApiController.getSelected().getAuthors());
        selected.setPublisher(booksApiController.getSelected().getPublisher());

        String dateString = booksApiController.getSelected().getPublishedDate();

        // Set the date format for use in MySQL
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Convert dateString from String type to java.util.Date type
            java.util.Date dateUtil = dateFormat.parse(dateString);

            // Convert date type from java.util.Date to java.sql.Date
            // publishedDate is declared to be of type java.sql.Date (MySQL date type)
            java.sql.Date dateInSqlFormat = new java.sql.Date(dateUtil.getTime());

            selected.setPublishedDate(dateInSqlFormat);

        } catch (ParseException ex) {
            // Unsuccessful parsing of the API date to SQL type
            selected.setPublishedDate(null);
        }

        selected.setDescription(booksApiController.getSelected().getDescription());
        selected.setIsbn13(booksApiController.getSelected().getIsbn13());
        selected.setPageCount(booksApiController.getSelected().getPageCount());
        selected.setPrintType(booksApiController.getSelected().getPrintType());
        selected.setImageUrl(booksApiController.getSelected().getImageUrl());
        selected.setPreviewUrl(booksApiController.getSelected().getPreviewUrl());

        create();

        listOfBooks = null;

        return "/book/List?faces-redirect=true";
    }

}