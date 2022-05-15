package bll;

import java.util.Date;

public interface IDeliveryServiceProcessing {
    /**
     * Adauga un produs nou la meniu
     *
     * @param m produsul
     * @pre m != null;
     * @post meniul contine produsul creat
     * @invariant menu!=null
     */
    public void importProducts(MenuItem m);

    /**
     * Sterge un produs
     *
     * @param str numele produsului care trebuie sters
     * @pre str != "";
     * @post none
     * @invariant menu!=null
     */

    public void deleteProducts(String str);

    /**
     * editeaza un produs
     */

    public void editProducts();

    /**
     * Genereaza un raport cu comenzile cuprinze intre doua ore date ca si parametru
     *
     * @param start ora de inceput
     * @param end   ora de sfarsit
     * @pre start > 0 and end > 0;
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */

    public void generateReport1(int start, int end);

    /**
     * Genereaza un raport cu produsele comandate de mai multe ori decat numarul dat ca parametru
     *
     * @param nr numarul minim de comenzi al unui produs
     * @pre nr > 0;
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */

    public void generateReport2(int nr);

    /**
     * Genereaza un raport cu clientii care au comandat de mai mult decat un anumit numar si de o valoare mai mare decat o valoare data
     *
     * @param nr  numarul minim de comenzi
     * @param val valoarea minima a comenzii
     * @pre nr > 0 and val > 0;
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */

    public void generateReport3(int nr, int val);

    /**
     * Genereaza un raport cu comenzile realizate intr-o anumita zi
     *
     * @param day ziua
     * @pre day != "";
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */

    public void generateReport4(String day);

    /**
     * Creaza un produs compus
     *
     * @param title numele produsului compus
     * @pre title != "";
     * @post produsul compus != null
     * @invariant menu!=null
     */

    public void completeCompositeProduct(String title);

    /**
     * Adauga produsul dorit la comanda
     *
     * @param orderID id comenzii
     * @pre orderID >=0;
     * @post produsul dorit!=null si prosusul a fost adaugat cu succes la comanda
     * @invariant menu!=null
     */

    public void addToOrder(int orderID);

    /**
     * Creaza o comanda noua
     *
     * @param orderID   id comanda
     * @param orderDate data
     * @param clientID  id client
     * @pre orderID >= 0 and orderDate != null and clientID >= 0;
     * @post lista cu produsele comandate != null;
     * @invariant menu!=null
     */

    public void createNewOrder(int orderID, Date orderDate, int clientID);

    /**
     * Cauta un produs in meniu
     *
     * @param title    numele produsului
     * @param rating   rating comansa
     * @param calories calorii
     * @param proteins proteine
     * @param fats     grasimi
     * @param sodium   sodiu
     * @param price    pretul
     * @pre title!=""and rating!="" and calories!=""and proteins!=""and fats!="" and sodium!=""and price!="";
     * @post none
     * @invariant menu!=null
     */

    public void searchProduct(String title, String rating, String calories, String proteins, String fats, String sodium, String price);
}

