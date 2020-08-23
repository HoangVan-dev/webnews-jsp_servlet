package com.hvn.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hvn.constant.SystemConstant;
import com.hvn.model.NewModel;
import com.hvn.paging.PageRequest;
import com.hvn.paging.Pageble;
import com.hvn.service.INewService;
import com.hvn.sort.Sorter;
import com.hvn.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Inject
	private INewService newService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NewModel model = FormUtil.toModel(NewModel.class, request);
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), 
											new Sorter(model.getSortName(), model.getSortBy()));
		 
		/*ĐƯợc thay thế bởi formUtils
		 * String pageStr = request.getParameter("page");
		String maxPageItem = request.getParameter("maxPageItem");
		if(pageStr !=null) {
			model.setPage(Integer.parseInt(pageStr));
		}else {
			model.setPage(1);
		}
		if(maxPageItem !=null) {
			model.setMaxPageItem(Integer.parseInt(maxPageItem));
		}*/
		
		
		model.setListResult(newService.findAll(pageble));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/ model.getMaxPageItem()) );
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/news/list.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
