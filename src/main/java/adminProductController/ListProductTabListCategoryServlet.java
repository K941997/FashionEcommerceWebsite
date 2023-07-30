package adminProductController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

@WebServlet(urlPatterns = {"/list-product-tablist-category"})
public class ListProductTabListCategoryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = new ArrayList<Product>();
		CategoryDAO categoryDAO = new CategoryDAO();
		
		products = productDAO.getAll(); 
		
		//Nếu lỗi Prices FilterCheck -> tach rieng Filter Prices
		
//		//TabList (Lọc theo 1 CategoryId) - Lấy danh sách Categories:
		List<Category> categories = categoryDAO.getAll();
		boolean[] chid = new boolean[categories.size()+1];
		req.setAttribute("categories", categories);
		req.setAttribute("cid", 0);
		req.setAttribute("chid", chid);
				
		//TabList (Lọc theo 1 CategoryId) - Lấy CategoryId trên Url:
		String categoryId_raw= req.getParameter("categoryId"); //id tren thanh url
		
		int categoryId = 0;
		
		if(categoryId_raw != null) {
			categoryId = Integer.parseInt(categoryId_raw);
			
			//TabList (Lọc theo 1 CategoryId) - Lấy danh sách Products theo CategoryId:
			products = productDAO.getProductsByCategoryId(categoryId);
			
			if(categoryId == 0) {
				chid[0] = true;
				products = productDAO.getAll();
			} 
			
			req.setAttribute("categoryId", categoryId);
		} 
		
		if(categoryId_raw == null) {
			chid[0] = true;
			
		}

			
//		//FilterCheckbox Categories (Lọc theo Nhiều CategoryIds): Tách riêng JSP/Servlet
		String[] categoriesIds_raw = req.getParameterValues("categoriesIds");
		int[] categoriesIds = null;
		if(categoriesIds_raw != null) {
			categoriesIds = new int[categoriesIds_raw.length];
			for(int i = 0; i < categoriesIds.length; i++) {
				categoriesIds[i] = Integer.parseInt(categoriesIds_raw[i]);
			}
			products = productDAO.getProductsByCategoryIds(categoriesIds);
		}
		if((categoriesIds_raw != null) && (categoriesIds[0] != 0)) {
			chid[0] = false;
			for(int i = 1; i < chid.length; i++) {
				if(isCheck(categories.get(i - 1).getCategoryId(), categoriesIds)) {
					chid[i] = true;
				} else {
					chid[i] = false;
				}
			}
		}
		
//		//FilterCheckbox Prices (Lọc theo Nhiều Prices): Tách riêng JSP/Servlet:
		String[] pp = {"Duoi 100 nghin",
		               "Tu 100 - 300 nghin",
		               "Tu 300 - 500 nghin",
		               "Tu 500 - 1 trieu",
		               "Tren 1 trieu"};
		boolean[] pb = new boolean[pp.length+1];
		pb[0] = true;
		List<Product> productsPriceDECS = productDAO.getProductsByDecreasePrice();
		List<Product> productsPriceASC = productDAO.getProductsByAscendingPrice();
		req.setAttribute("productsPriceDECS", productsPriceDECS );
		req.setAttribute("productsPriceASC", productsPriceASC);
		req.setAttribute("pp", pp);
		req.setAttribute("pb", pb);
		
		//FilterCheckbox Prices:
		String[] price = req.getParameterValues("productPrices");
		if(price != null) {
			int from = 0, to = 0;
			for(int i = 0 ; i < price.length; i++) {
				List<Product> temp = new ArrayList<>();
				if(price[i].equals("0")) {
					from = 0;
					to = 2000000;					
					products = productDAO.getProductsByPrice(from, to);
					pb[0] = true;
					break;
				} else {
					if(price[i].equals("1")) {
						from = 0;
						to = 100000;
						temp = productDAO.getProductsByPrice(from, to);
						products.addAll(temp);
						pb[1] = true;
					}
					if(price[i].equals("2")) {
						from = 100000;
						to = 300000;
						temp = productDAO.getProductsByPrice(from, to);
						products.addAll(temp);
						pb[2] = true;
					}
					if(price[i].equals("3")) {
						from = 300000;
						to = 500000;
						temp = productDAO.getProductsByPrice(from, to);
						products.addAll(temp);
						pb[3] = true;
					}
					if(price[i].equals("4")) {
						from = 500000;
						to = 1000000;
						temp = productDAO.getProductsByPrice(from, to);
						products.addAll(temp);
						pb[4] = true;
					}
					if(price[i].equals("5")) {
						from = 1000000;
						to = 2000000;
						temp = productDAO.getProductsByPrice(from, to);
						products.addAll(temp);
						pb[5] = true;
					}
				}
			}
		}
		if(price == null) {
			pb[0] = true;
		}
		
		
//		//Paginate (Phân trang) - Phân trang theo Danh Sách Products Theo CategoryId:
		int page, numberPage = 6;
		int size = products.size();
		int num = (size%6==0?(size/6):((size/6))+1);
		String xPage = req.getParameter("page");
		if(xPage == null) {
			page = 1;
		} else {
			page = Integer.parseInt(xPage);
		}
		int start, end;
		start = (page-1)*numberPage;
		end = Math.min(page*numberPage, size);
		
		List<Product> productsPagination = new ArrayList<Product>();
		productsPagination = productDAO.getProductsByPage(products, start, end);
		
		req.setAttribute("productsPagination", productsPagination);
		req.setAttribute("page", page);
		req.setAttribute("num", num);

		//Final:
		req.setAttribute("products", products);
		req.getRequestDispatcher("/views/admin/list_product_tablist_category.jsp").forward(req, resp);
	}
	
//	//Dành cho FilterCheckbox Categories:
	private boolean isCheck(int d, int[] id) {
		if(id == null) {
			return false;
		} else {
			for(int i = 0; i < id.length; i++) {
				if(id[i]==d) {
					return true;
				}
			}
		}
		return false;
	}
}
