package packageModels;

import packageMain.DateUtil;
import packageMain.JDBC;
import java.sql.*;
import java.time.LocalDate;

public class PdfGestionDAO {

    //Attributs to controll the connection into the methods
    Connection con = null;
    PreparedStatement ps = null;

    private static PdfGestionDAO instance = null;

    //Attributs string containing the differents sql query we need
    private final static String SELECT_ALL_PDF_SQL = "SELECT id_pdf,name_pdf,path_pdf,upload_date,status_public FROM pdf";
    private final static String CREATE_PDF_SQL = "INSERT INTO pdf (name_pdf,path_pdf,upload_date,status_public) VALUES (?,?,?,?)";
    private final static String SELECT_PDF_BY_NAME_SQL = "SELECT id_pdf,name_pdf,path_pdf,upload_date,status_public FROM pdf WHERE name_pdf=?";

    /**
     * Constructor extends constructor PdfGestionList for the singleton
     */
    public PdfGestionDAO(){
        super();
    }

    /**
     * Method how allow to get the pdfList instance
     * @return instance of the PdfGestionDAO class
     */
    public static PdfGestionDAO getInstance(){

        if(instance == null){

            instance = new PdfGestionDAO();
        }

        return instance;
    }

    /**
     * Method how execute SQL query creating pdf and send on BD, call addPdf of the class PdfGestList
     * @param pdf
     */
    public void addPdf(Pdf pdf){

        if(PdfGestionList.getInstance().searchPdfByName(pdf.getName()) == false){

            try {

                con = JDBC.getInstance().getConnection();

                ps = con.prepareStatement(CREATE_PDF_SQL);

                ps.setString(1, pdf.getName());
                ps.setString(2, pdf.getPathPdf());
                //TODO passer par un setDate et changer je type de du champ BD en Date (ça marche mais la requete génère des '' que le type date de la bd n'aime pas)
                //ps.setDate(6, new java.sql.Date(DateUtil.asDate(user.getBirthday()).getTime()));
                ps.setString(3, DateUtil.format(pdf.getUploadDate()));
                ps.setBoolean(4, pdf.getStatus());

                ps.execute();

                ps.close();

            }catch (SQLException ex){

                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                System.exit(-1);

            } finally {
                if ( con != null ){
                    JDBC.getInstance().closeConnection(con);

                    addPdfIntoListAfterCreate(pdf);
                }
            }
        }
    }


        /**
         * Utilitary method for addPdf call at last. Allow to insert the last created Pdf into the list
         * @param pdf
         */
    public void addPdfIntoListAfterCreate(Pdf pdf){

        Pdf pdfToPutInList = new Pdf(1,"password", "nicolas", LocalDate.of(1994, 2, 21), false);

        try{

            con = JDBC.getInstance().getConnection();

            ps = con.prepareStatement(SELECT_PDF_BY_NAME_SQL);

            ps.setString(1, pdf.getName());

            ResultSet result = ps.executeQuery();

            result.next();

            pdfToPutInList.setId(result.getInt("id_pdf"));
            pdfToPutInList.setName(result.getString("name_pdf"));
            pdfToPutInList.setPathPdf(result.getString("path_pdf"));
            pdfToPutInList.setStatus(result.getBoolean("status_public"));
            pdfToPutInList.setUploadDate(DateUtil.parse(result.getString("upload_date")));

            PdfGestionList.getInstance().addPdf(pdfToPutInList);

        }catch(SQLException ex){

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);

        }finally{
            if ( con != null ){
                JDBC.getInstance().closeConnection(con);
            }
        }
    }

    /**
     * Method called to charge DB table pdf into listPdf
     */
    public void loadPdfintoList(){

        try {

            con = JDBC.getInstance().getConnection();

            ps = con.prepareStatement(SELECT_ALL_PDF_SQL);

            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Pdf newPdf = new Pdf(1, "a", "path" , LocalDate.of(0001, 1, 01), false);

                newPdf.setId(result.getInt("id_pdf"));
                newPdf.setName(result.getString("name_pdf"));
                newPdf.setPathPdf(result.getString("path_pdf"));
                newPdf.setUploadDate(DateUtil.parse(result.getString("birthday_date")));
                newPdf.setStatus(result.getBoolean("status_public"));

                Pdf pdf = PdfGestionList.getInstance().getPdfById(newPdf.getId());

                if (PdfGestionList.getInstance().searchPdfByName(newPdf.getName()) == false && pdf == null) {

                    PdfGestionList.getInstance().addPdf(newPdf);
                }
            }

            ps.close();

        }catch (SQLException ex){

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);

        } finally {
            if ( con != null ){
                JDBC.getInstance().closeConnection(con);
            }
        }
    }
}
