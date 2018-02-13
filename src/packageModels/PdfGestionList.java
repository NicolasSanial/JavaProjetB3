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

    // Instance initialisation
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

    /**
     * Method who take the choosen pdf to save in it local in a project folder. Allowing the recuperation
     * of pdf file with path into BDD
     * @param pdf : file to save in the desired folder
     * @param originPath : default path of the pdf
     * @return path of the new pdf to save it in BD
     */
    public String moveFileToFolder(Pdf pdf, String originPath) {

        File afile = new File(originPath);

        String name = pdf.getName();

        // CARE ABOUT THE FOLDER PATH (Win = C:/Users/TyraeliuM/Documents/PDF AND Linux = /home/sanial/IdeaProjects/JavaProjectB3/PdfFolder/)
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

        }catch(Exception e){

            e.printStackTrace();
        }

        return bfile.getPath();
    }
}