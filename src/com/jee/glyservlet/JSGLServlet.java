package com.jee.glyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jee.common.Database;
import com.jee.xtservlet.CZRZGZ;

public class JSGLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Database db = new Database();
		
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		String juese = "";
		int xssh = 0;
		int bmsh = 0;
		int tjcx = 0;
		int xqzj = 0;
		int xqgl = 0;
		int yhxx = 0;
		int xgmm = 0;
		
		juese = req.getParameter("juese");
		String[] xsshqx = req.getParameterValues("xsshqx");
		if(xsshqx != null){
			String xsshqx1 = xsshqx[0];
			xssh = Integer.parseInt(xsshqx1);
		}
		String[] bmshqx = req.getParameterValues("bmshqx");
		if(bmshqx != null){
			String bmshqx1 = bmshqx[0];
			bmsh = Integer.parseInt(bmshqx1);
		}
		String[] tjcxqx = req.getParameterValues("tjcxqx");
		if(tjcxqx != null){
			String tjcxqx1 = tjcxqx[0];
			tjcx = Integer.parseInt(tjcxqx1);
		}
		String[] xqzjqx = req.getParameterValues("xqzjqx");
		if(xqzjqx != null){
			String xqzjqx1 = xqzjqx[0];
			xqzj = Integer.parseInt(xqzjqx1);
		}
		String[] xqglqx = req.getParameterValues("xqglqx");
		if(xqglqx != null){
			String xqglqx1 = xqglqx[0];
			xqgl = Integer.parseInt(xqglqx1);
		}
		String[] yhxxqx = req.getParameterValues("yhxxqx");
		if(yhxxqx != null){
			String yhxxqx1 = bmshqx[0];
			yhxx = Integer.parseInt(yhxxqx1);
		}
		String[] xgmmqx = req.getParameterValues("xgmmqx");
		if(xgmmqx != null){
			String xgmmqx1 = xgmmqx[0];
			xgmm = Integer.parseInt(xgmmqx1);
		}
		
		String strUserName = (String)req.getSession().getAttribute("user_in_session");
		CZRZGZ cz = new CZRZGZ();
		cz.CZMoKuai = "管理员";
		cz.CZMing = "角色管理";
		cz.CZYuan = strUserName;
		cz.CZTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String czSql = "insert into CaoZuoJiLu values('"+cz.CZMoKuai+"','"+cz.CZMing+"','"+cz.CZYuan+"','"+cz.CZTime+"'";
		
		
		String sql = "update JueSe set xqzj = '"+xqzj+"',xqgl = '"+xqgl+"',	yhxx = '"+yhxx+"',yhxg = '"+xgmm+"',xssh = '"+xssh+"',bmsh = '"+bmsh+"',tjcx = '"+tjcx+"' where juese = '"+juese+"'";
		System.out.println(sql);
		int i = db.executeUpdate(sql);
		if(i == 1){
			czSql += ",'成功','角色权限设置成功')";
			db.executeUpdate(czSql);
			
			PrintWriter out = resp.getWriter();//对象,输出流
			out.print("<script>alert('保存成功!');history.back()</script>");
			return;
			
		}
		else{
			czSql += ",'失败','角色权限设置失败')";
			db.executeUpdate(czSql);
			
			PrintWriter out = resp.getWriter();//对象,输出流
			out.print("<script>alert('保存失败!');history.back()</script>");
			return;
		}
	}

}
