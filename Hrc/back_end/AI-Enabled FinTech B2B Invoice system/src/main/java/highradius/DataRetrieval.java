package highradius;

import java.io.IOException;
import java.net.MulticastSocket;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

/**
 * Servlet implementation class DataRetrieval
 */
@WebServlet("/DataRetrieval")
public class DataRetrieval extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataRetrieval() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String pageInURL = request.getParameter("page");
			int page = Integer.parseInt(pageInURL);
			page = (page-1) * 10;
			Connection con = DBConnection.createConnection();
			Statement st = con.createStatement();
			String query = "SELECT sl_no, business_code, cust_number, clear_date, buisness_year, doc_id, posting_date, document_create_date, due_in_date, invoice_currency, document_type, posting_id, total_open_amount, baseline_create_date, cust_payment_terms, invoice_id from winter_internship LIMIT " +page+", "+10;
			ResultSet rs = st.executeQuery(query);
			page = 0;

			ArrayList<Response> res = new ArrayList<>();
			
			while(rs.next()) {
				Response resp = new Response();
				resp.setSl_no(rs.getInt("sl_no"));
				resp.setBusiness_code(rs.getString("business_code"));
				resp.setCust_number(rs.getInt("cust_number"));
				resp.setClear_date(rs.getString("clear_date"));
				resp.setBuisness_year(rs.getString("buisness_year"));
				resp.setDoc_id(rs.getString("doc_id"));
				resp.setPosting_date(rs.getString("posting_date"));
				resp.setDocument_create_date(rs.getString("document_create_date"));
				resp.setDue_in_date(rs.getString("due_in_date"));
				resp.setInvoice_currency(rs.getString("invoice_currency"));
				resp.setDocument_type(rs.getString("document_type"));
				resp.setPosting_id(rs.getInt("posting_id"));
				resp.setTotal_open_amount(rs.getString("total_open_amount"));
				resp.setBaseline_create_date(rs.getString("baseline_create_date"));
				resp.setCust_payment_terms(rs.getString("cust_payment_terms"));
				resp.setInvoice_id(rs.getInt("invoice_id"));
				res.add(resp);
			}

			for(int i = 0; i < res.size(); i++) {
				System.out.println(res.get(i).toString());
			}
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			String jsonData = gson.toJson(res);
			response.setContentType("application/json");
			
			try {
				response.getWriter().write(jsonData);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
