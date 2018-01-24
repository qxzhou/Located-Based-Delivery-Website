package rpc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")     /*对应url中的endpoint部分*/
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*");     //所有人都可以访问search的service
//		response.setContentType("text/html");
		response.setContentType("application/json");
		
		PrintWriter out= response.getWriter();
		
//		if (request.getParameter("username") != null) {
//			String username = request.getParameter("username");
//			out.print("Hello " + username);
//		}
		
//		out.println("<html><body>");
//		out.println("<h1>This is a html page</h1>");
//		out.println("</body></html>");
		
//		String username = "";
//		if (request.getParameter("username") != null) {
//			username = request.getParameter("username");
//		}
		
		
//		JSONObject obj = new JSONObject();
//		try {
//			obj.put("username", username);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		out.print(obj);
//		
		JSONArray array = new JSONArray();
		try {
			array.put(new JSONObject().put("username","abcd"));
			array.put(new JSONObject().put("username", "1234"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		out.print(array);
		
		out.flush();   //表示内容已经写完，需要tomcat返回给前端
		out.close();		//关闭内存
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
