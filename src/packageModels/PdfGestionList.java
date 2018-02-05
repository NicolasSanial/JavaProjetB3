package packageModels;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfGestionList {


    /**
     * Constructor for the singleton
     */
    public PdfGestionList() { }

    // initialisation du singleton
    private static PdfGestionList instance = null;

    /**
     * Method how allow to get the userList instance
     */
    public static PdfGestionList getInstance(){
        if(instance == null){
            instance = new PdfGestionList();
        }
        return instance;
    }

    // Init of the only one user list in the appli
    private List<Pdf> listPdf = new ArrayList<Pdf>();

    /**
     * Return the list
     */
    public List<Pdf> getListPdf() {
        return listPdf;
    }

    //RESEARCH METHOD

    /**
     * Method how search if user login exist or not
     * @param name
     */
    public boolean searchPdfByName(String name){
        boolean result = false;
        Iterator<Pdf> it = listPdf.iterator();
        while(it.hasNext()){
            Pdf pdf = it.next();
            if(pdf.getName().equals(name)){
                result = true;
            }
        }
        return result;
    }

    /**
     * Method how search object user with login
     * @param name
     */
    public Pdf searchObjPdfByName (String name){
        Pdf p = null;
        Iterator<Pdf> it = listPdf.iterator();
        while(it.hasNext()){
            Pdf pdf = it.next();
            if(pdf.getName().equals(name)){
                p = pdf;
            }
        }
        return p;
    }

    /**
     * Method how search object pdf with id
     * @param id
     */
    public Pdf getPdfById(int id){
        Pdf p = null;
        Iterator<Pdf> it = listPdf.iterator();
        while(it.hasNext()){
            Pdf pdf = it.next();
            if(pdf.getId() == id){
                p = pdf;
            }
        }
        return p;
    }

    /**
     * Method how add pdf into list
     * @param pdf
     */
    public void addPdf (Pdf pdf){
        Pdf p = getPdfById(pdf.getId());
        if(searchPdfByName(pdf.getName())== false && p == null){
            listPdf.add(pdf);
        }
    }

    /**
     * Method how add pdf into list
     * @param pdf
     */
    public void modifyPdf(Pdf pdf){
        Pdf p = getPdfById(pdf.getId());
        if(searchPdfByName(pdf.getName()) == true && p != null){
            p.setName(pdf.getName());
            p.setStatus(pdf.getStatus());
        }
    }

    /**
     * Method to call to remove pdf by id (call removePdfList(Pdf) with an pdf parameter
     * @param id
     */
    public void removePdfById(int id){
        Pdf p = getPdfById(id);
        if (p != null){
            listPdf.remove(p);
        }
    }

    /**
     * Method how remove pdf from list
     * @param pdf
     */
    public void removePdfByObJ(Pdf pdf){
        if(searchPdfByName(pdf.getName()) == true && pdf != null) {
            listPdf.remove(pdf);
        }
    }

    public String moveFileToFolder(File file, String originPath) {

        File afile = new File(originPath);
        String name = afile.getName();
        File bfile = new File("/home/sanial/IdeaProjects/JavaProjectB3/PdfFolder/"+ name);

        InputStream inStream = null;
        OutputStream outStream = null;

        try{
            int length;

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            //delete the original file
            //afile.delete();

        }catch(Exception e){
            e.printStackTrace();
        }

        return bfile.getPath();
    }

    /**
     *  Method how load test data in list when application start
     */
    /*
    public void loadPdfintoList(){
*/
        /* On va aller récup dans la BD plus tard, là on met des données en dur */
        /*
        listPdf.add(new Pdf(1, "pdf1",  LocalDate.of(1994, 2, 21), true));
        listPdf.add(new Pdf(2, "pdf2",  LocalDate.of(1995, 2, 21), true));
        listPdf.add(new Pdf(3, "pdf3",  LocalDate.of(1996, 2, 21), true));
    }
    */
}