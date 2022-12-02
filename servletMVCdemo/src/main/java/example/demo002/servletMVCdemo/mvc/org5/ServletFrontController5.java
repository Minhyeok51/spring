package example.demo002.servletMVCdemo.mvc.org5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.DemoAdapter;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import example.demo002.servletMVCdemo.mvc.org3.DemoUnitInsertController3;
import example.demo002.servletMVCdemo.mvc.org3.DemoUnitNewController3;
import example.demo002.servletMVCdemo.mvc.org3.DemoUnitSelectController3;
import example.demo002.servletMVCdemo.mvc.org4.DemoUnitInsertController4;
import example.demo002.servletMVCdemo.mvc.org4.DemoUnitNewController4;
import example.demo002.servletMVCdemo.mvc.org4.DemoUnitSelectController4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletFrontController5" , urlPatterns ="/controller/org5/*")
public class ServletFrontController5 extends HttpServlet{

	private Map<String, Object> map = new HashMap<>();
	//Map에 부모인 인터페이스를 담아둬 상속받은 나머지 컨트롤러들도 모두 포함시킨다
	private List<DemoAdapter> adapterList = new ArrayList<>();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();

		Object objCon = map.get(uri);
		DemoAdapter adapter = null;

		for(DemoAdapter da : adapterList) {
			if(da.isHandled(objCon)) {
				adapter = da;
			}
		}
		DemoModelView dmv =  adapter.process(req, resp, objCon);

		DemoView dv = viewResolver(dmv.getView()); //demoUnitList만 들어간 dmv.getView()
		dv.render(req, resp, dmv.getModel());
	}
	public ServletFrontController5() {
		//Controller3
		map.put("/controller/org5/3/new", new DemoUnitNewController3());
		map.put("/controller/org5/3/insert", new DemoUnitInsertController3());
		map.put("/controller/org5/3/select", new DemoUnitSelectController3());


		//Controller4
		DemoUnitNewController4 dnCon4 = new DemoUnitNewController4();
		DemoUnitInsertController4 diCon4 = new DemoUnitInsertController4();
		DemoUnitSelectController4 dsCon4 = new DemoUnitSelectController4();

		map.put("/controller/org5/4/new",  dnCon4);
		map.put("/controller/org5/4/insert", diCon4);
		map.put("/controller/org5/4/select", dsCon4);

		DemoAdapter3 dAdapter = new DemoAdapter3();
		adapterList.add(dAdapter);

		DemoAdapter4 dAdapter4 = new DemoAdapter4();
		adapterList.add(dAdapter4);


	}

	private DemoView viewResolver(String view) {//경로가 바뀌어도 쉽게 수정가능 
		view = "/WEB-INF/views/" + view + ".jsp";
		DemoView dv = new DemoView(view);
		return dv;

	}
}
