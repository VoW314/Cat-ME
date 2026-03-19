package edu.vt.controllers;

import edu.vt.EntityBeans.Cat;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.CatFacade;
import edu.vt.globals.Methods;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

@Named("catController")
@SessionScoped
public class CatController implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private List<Cat> listOfCats = null;
    private Cat selected;

   /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    UserFacade bean into the instance variable 'userFacade' after it is instantiated at runtime.
     */
    @EJB
    private CatFacade catFacade;


    /*
=========================
Getter and Setter Methods
=========================
 */
    public List<Cat> getListOfCats() {
        System.out.println("ListOfCats was called");
        if (listOfCats == null) {
            listOfCats = catFacade.findAll();

        }
        return listOfCats;
    }


    // getter and setter for selecting cats
    public Cat getSelected() {
        return selected;
    }
    public void setSelected(Cat selected){
        this.selected = selected;
    }

    //unselct the cat
    public void unselect(){
    selected = null;
    }

    // cancel the cat
    public String cancel() {
        selected = null;
        return "/favoriteCatBreeds/DataTable?faces-redirect=true";

    }

    // creation
    public void prepareCreate(){
        selected = new Cat();
    }

    // Create a new cat in the database
    public void create(){
        Methods.preserveMessages();
        persist(PersistAction.CREATE, "Cat was created");

        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            listOfCats = null;
        }
    }


    //Update the selected cat in the database
    public void update() {
        Methods.preserveMessages();

        persist(PersistAction.UPDATE, "Cat was updated");

        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            listOfCats = null;
        }
    }

    // Delete the cat from the database
    public void destroy() {
        Methods.preserveMessages();

        persist(PersistAction.DELETE, "Cat was Successfully Deleted!");

        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            listOfCats = null;
        }
    }

    /*
     **********************************************************************************************
     *   Perform CREATE, UPDATE (EDIT), and DELETE (DESTROY, REMOVE) Operations in the Database   *
     **********************************************************************************************
     */
    /**
     * @param persistAction refers to CREATE, UPDATE (Edit) or DELETE action
     * @param successMessage displayed to inform the user about the result
     */

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).

                     catFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    catFacade.edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove(selected) method performs the DELETE operation of the "selected"
                     object in the database.

                     catFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    catFacade.remove(selected);
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



}





