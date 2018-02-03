package packageModels;

import packageMain.DateUtil;
import packageMain.JDBC;

import java.sql.*;
import java.time.LocalDate;

public class PdfGEstionDAO {

    //Attributs to controll the connection into the methods
    Connection con = null;
    PreparedStatement ps = null;

    private static PdfGEstionDAO instance = null;

    //Attributs string containing the differents sql query we need
    //TODO : Utiliser id_user avec foreign key pour mise en place session et pdf privé
    private final static String SELECT_ALL_PDF_SQL = "SELECT id_pdf,name_pdf,path_pdf,upload_date,status_public FROM pdf";

    /**
     * Constructor extends constructor UserGestionList for the singleton
     */
    public PdfGEstionDAO(){
        super();
    }

    /**
     * Method how allow to get the userList instance
     * @return instance of the UserGestionDAO class
     */
    public static PdfGEstionDAO getInstance(){

        if(instance == null){

            instance = new PdfGEstionDAO();
        }

        return instance;
    }

    /**
     * Method called to charge DB table j_user into listUser
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

                if (UserGestionList.getInstance().searchUserByLogin(newPdf.getName()) == false && pdf == null) {

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
