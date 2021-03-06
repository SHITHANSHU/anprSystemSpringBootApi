package com.anpr.demo.controller;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.anpr.demo.api.carrecord;
import com.anpr.demo.api.login;
import com.anpr.demo.api.numberplate;
import com.anpr.demo.api.session;
import com.anpr.demo.api.tolldata;
import com.anpr.demo.api.tollrecord;
import com.anpr.demo.repo.carrecordRep;
import com.anpr.demo.repo.loginRep;
import com.anpr.demo.repo.numberplateRep;
import com.anpr.demo.repo.sessionRep;
import com.anpr.demo.repo.tolldataRep;
import com.anpr.demo.repo.tollrecordRep;
import com.itextpdf.text.DocumentException;


@CrossOrigin
@RestController
@RequestMapping("anpr")
public class loginController {

	@Autowired
	private loginRep loginrep;
	@Autowired
	private numberplateRep numrep;
	@Autowired
	private sessionRep sessionrep;
	@Autowired
	private tolldataRep tolldatarep;
	@Autowired
	private tollrecordRep tollrecordrep;
	@Autowired
	private carrecordRep carrecordrep; 
	
	@GetMapping("getalllogin")
	public List<login> getallLogin()
	{
		return this.loginrep.findAll();
	}
	
	@GetMapping("insertlogin/{id}/{pas}/{type}")
	public List<login> insertLohin(@PathVariable("id") String id,@PathVariable("pas") String pass,@PathVariable("type") int type)
	{
		login l=new login(id,pass,type);
		this.loginrep.save(l);
		return this.loginrep.findAll();
	}
	
	
	@GetMapping("getall")
	public List<numberplate> getallNumberPlate()
	{
		return this.numrep.findAll();
	}
	
	@GetMapping("initall/numdatav")
	public List<numberplate> initall() throws DocumentException, IOException
	{
		List<numberplate> n=this.getallNumberPlate();
		for(numberplate num:n)
		{
		System.out.println(System.getProperty("java.class.path"));
		Context context = new Context();
        numberplate k=num;
        context.setVariable("name",k.getName());
        context.setVariable("lic",k.getLicense());
        context.setVariable("nump",k.getId());
        context.setVariable("mobile",k.getMobile());
        context.setVariable("type",this.gettypeString(k.getType()));

        context.setVariable("tollpaid",0);
        
        context.setVariable("dueamnt",k.getToll());
        context.setVariable("date",new java.util.Date());
        String message="the account was created";
        context.setVariable("msg", message);

        
        String htmlContentToRender = templateEngine.process("template", context);
        String xHtml = xhtmlConvert(htmlContentToRender);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources","templates")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        java.io.OutputStream outputStream = new FileOutputStream("src/main/resources/temp_download/noc"+num.getId()+".pdf");
        renderer.createPDF(outputStream);
        outputStream.close();
		}
		return this.numrep.findAll();
	}

	
	
	@GetMapping("insert/{id}/{lic}/{mobile}/{name}/{toll}/{type}")
	public List<numberplate> insertNumberPlate(@PathVariable("id") String id,@PathVariable("lic") String lic,@PathVariable("mobile") int mobile,@PathVariable("name") String name,@PathVariable("toll") int toll,@PathVariable("type") int type) throws DocumentException, IOException
	{
		numberplate num=new numberplate(id, lic, mobile, name, toll,type);
		this.numrep.save(num);
		System.out.println(System.getProperty("java.class.path"));
		Context context = new Context();
        numberplate k=num;
        context.setVariable("name",k.getName());
        context.setVariable("lic",k.getLicense());
        context.setVariable("nump",k.getId());
        context.setVariable("mobile",k.getMobile());
        context.setVariable("type",this.gettypeString(k.getType()));

        context.setVariable("tollpaid",0);
        
        context.setVariable("dueamnt",k.getToll());
        context.setVariable("date",new java.util.Date());
        String message="the account was created";
        context.setVariable("msg", message);

        
        String htmlContentToRender = templateEngine.process("template", context);
        String xHtml = xhtmlConvert(htmlContentToRender);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources","templates")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        java.io.OutputStream outputStream = new FileOutputStream("src/main/resources/temp_download/noc"+num.getId()+".pdf");
        renderer.createPDF(outputStream);
        outputStream.close();

		return this.numrep.findAll();
	}
	
	@GetMapping("update/{id}/{lic}/{mobile}/{name}/{toll}/{type}")
	public List<numberplate> updateNumberPlate(@PathVariable("id") String id,@PathVariable("lic") String lic,@PathVariable("mobile") int mobile,@PathVariable("name") String name,@PathVariable("toll") int toll,@PathVariable("type") int type)
	{
		Optional<numberplate> nu=this.numrep.findById(id);
		numberplate su;
		if(nu.isPresent())
		{
			su=nu.get();
			su.setLicense(lic);
			su.setMobile(mobile);
			su.setName(name);
			su.setToll(toll);
			su.setType(type);

			this.numrep.save(su);
		}
//		this.numrep.
		return this.numrep.findAll();
	}

	public String gettypeString(int a)
	{
		switch(a)
		{
		case 1: return	"Car/SUV/Van";
		case 2: return	"Mini Bus";
		case 3: return	"Truck (2 axels)";
		case 4: return	"Bus";
		case 5: return	"Truck (>2 axel)";
		case 6: return	"Crane/multi axel vehicles";
		}
		
		return "vehicle";
	}
	
	
	
	@GetMapping("insertuser/{id}/{lic}/{mobile}/{name}/{toll}/{type}")
	public List<numberplate> insertNumberPlateuser(@PathVariable("id") String id,@PathVariable("lic") String lic,@PathVariable("mobile") int mobile,@PathVariable("name") String name,@PathVariable("toll") int toll,@PathVariable("type") int type) throws DocumentException, IOException
	{
		numberplate num=new numberplate(id, lic, mobile, name, toll,type);
		this.numrep.save(num);
		System.out.println(System.getProperty("java.class.path"));
		Context context = new Context();
        numberplate k=num;
        context.setVariable("name",k.getName());
        context.setVariable("lic",k.getLicense());
        context.setVariable("nump",k.getId());
        context.setVariable("mobile",k.getMobile());
        context.setVariable("type",this.gettypeString(k.getType()));

        context.setVariable("tollpaid",0);

        context.setVariable("dueamnt",k.getToll());
        context.setVariable("date",new java.util.Date());
        context.setVariable("msg", "the account was created");

        
        String htmlContentToRender = templateEngine.process("template", context);
        String xHtml = xhtmlConvert(htmlContentToRender);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources","templates")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        java.io.OutputStream outputStream = new FileOutputStream("src/main/resources/temp_download/noc"+num.getId()+".pdf");
        renderer.createPDF(outputStream);
        outputStream.close();

		return this.getallNumberPlatebyname(name);
	}

	
	public void createFile(String id) throws DocumentException, IOException
	{
		
		Optional<numberplate> num2=this.numrep.findById(id);
		numberplate num=num2.get();
		this.numrep.save(num);
		System.out.println(System.getProperty("java.class.path"));
		Context context = new Context();
        numberplate k=num;
        context.setVariable("name",k.getName());
        context.setVariable("lic",k.getLicense());
        context.setVariable("nump",k.getId());
        context.setVariable("mobile",k.getMobile());
        context.setVariable("type",this.gettypeString(k.getType()));

        context.setVariable("tollpaid",0);

        context.setVariable("dueamnt",k.getToll());
        context.setVariable("date",new java.util.Date());
        context.setVariable("msg", "the account was created");

        
        String htmlContentToRender = templateEngine.process("template", context);
        String xHtml = xhtmlConvert(htmlContentToRender);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources","templates")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        java.io.OutputStream outputStream = new FileOutputStream("src/main/resources/temp_download/noc"+num.getId()+".pdf");
        renderer.createPDF(outputStream);
        outputStream.close();


	}

	
	@GetMapping("updateuser/{id}/{lic}/{mobile}/{name}/{toll}/{type}")
	public List<numberplate> updateNumberPlateuser(@PathVariable("id") String id,@PathVariable("lic") String lic,@PathVariable("mobile") int mobile,@PathVariable("name") String name,@PathVariable("toll") int toll,@PathVariable("type") int type)
	{
		Optional<numberplate> nu=this.numrep.findById(id);
		numberplate su;
		if(nu.isPresent())
		{
			su=nu.get();
			su.setLicense(lic);
			su.setMobile(mobile);
			su.setName(name);
			su.setToll(toll);
			su.setType(type);

			this.numrep.save(su);
		}
//		this.numrep.
		return this.getallNumberPlatebyname(name);
	}

	@GetMapping("delete/{id}")
	public List<numberplate> deleteuser(@PathVariable("id") String id)
	{
		this.numrep.deleteById(id);
		File f=new File("src/main/resources/temp_download/noc"+id+".pdf");
		if(f.delete())
		{
			System.out.println("deleted");
		}
		return this.numrep.findAll();
	}
	
	@GetMapping("deleteuser/{id}/{name}")
	public List<numberplate> deleteuser(@PathVariable("id") String id,@PathVariable("name") String name)
	{
		this.numrep.deleteById(id);
		File f=new File("src/main/resources/temp_download/noc"+id+".pdf");
		if(f.delete())
		{
			System.out.println("deleted");
		}
		return this.getallNumberPlatebyname(name);
	}
	
	
	@GetMapping("getallbyname/{name}")
	public List<numberplate> getallNumberPlatebyname(@PathVariable("name") String name)
	{
		List<numberplate> num=this.numrep.findAll();
		
		List<numberplate> fi=new ArrayList<numberplate>();
		
		for(numberplate n : num)
		{
			if(n.getName().equals(name))
				fi.add(n);
		}
		
		return fi;
	}
	
	
	@GetMapping("getallcarrecord")
	public List<carrecord> getallCarRecord()
	{
		return this.carrecordrep.findAll();
	}
	
	@GetMapping("insertcarrecord/{num}/{tollam}/{tollco}")
	public List<carrecord> insertCarRecord(@PathVariable("num") String num,@PathVariable("tollam") int tollam,@PathVariable("tollco") String tollco)
	{
		carrecord c=new carrecord(num, tollam, new java.util.Date(), tollco);
		this.carrecordrep.save(c);
		return this.carrecordrep.findAll();
	}
	
	
	@GetMapping("getallsession")
	public List<session> getallSesssion()
	{
		return this.sessionrep.findAll();
	}
	
	@GetMapping("session/{username}/{password}/{type}")
	public session getsession(@PathVariable("username") String id,@PathVariable("password") String pass,@PathVariable("type") int type)
	{
		List<login> s=this.loginrep.findAll();
		
		for(login se:s)
		{
			if(se.getUname().equals(id) && se.getPassword().equals(pass) && se.getType()==type)
			{	
				session ses=new session(id,type);
				return ses;
			}
		}
		session ses=new session("&&invalid&&",0);
		return ses;
	}
	
	
	
	@GetMapping("insertsession/{id}/{type}")
	public List<session> insertSesssion(@PathVariable("id") String id,@PathVariable("type") int type)
	{
		session s=new session(id,type);
		this.sessionrep.save(s);
		return this.sessionrep.findAll();
	}	
	
	@GetMapping("getalltolldata")
	public List<tolldata> getallTollData()
	{
		return this.tolldatarep.findAll();
	}
	
	@GetMapping("gettolldata/{code}/{exception}")
	public List<tolldata> getTollData(@PathVariable("code") String id,@PathVariable("excep") String excep)
	{
		List<tolldata> t=this.tolldatarep.findAll();
		List<tolldata> tol=new ArrayList<tolldata>();
		for(tolldata toll:t)
		{
			if(toll.getCode().equals(id) && toll.getException().contentEquals(excep))
				tol.add(toll);
		}
		
		return tol;
	}
	
	@GetMapping("gettolldatabycode/{code}")
	public List<tolldata> gettolldatabycode(@PathVariable("code") String id)
	{
		List<tolldata> t=this.tolldatarep.findAll();
		List<tolldata> tol=new ArrayList<tolldata>();;
		for(tolldata toll:t)
		{
			if(toll.getCode().equals(id))
				tol.add(toll);
		}
		
		return tol;
	}
	

	
	@GetMapping("inserttolldata/{id}/{excep}")
	public List<tolldata> insertTollData(@PathVariable("id") String id,@PathVariable("excep") String excep)
	{
		tolldata t=new tolldata(id,excep);
		this.tolldatarep.save(t);
		return this.tolldatarep.findAll();
	}
	
	@GetMapping("deletetolldata/{code}/{exception}")
	public List<tolldata> deleteTollData(@PathVariable("code") String id,@PathVariable("excep") String excep)
	{
		List<tolldata> t=this.tolldatarep.findAll();
		
		for(tolldata toll:t)
		{
			if(toll.getCode().equals(id) && toll.getException().contentEquals(excep))
				this.tolldatarep.delete(toll);
		}
		
		return this.tolldatarep.findAll();
	}
	
	@GetMapping("getalltollrecorddata")
	public List<tollrecord> getallTollRecord()
	{
		return this.tollrecordrep.findAll();
	}
	
	@GetMapping("addtollrecord/{id}/{t1}/{t2}/{t3}/{t4}/{t5}/{t6}/{tr1}/{tr2}/{tr3}/{tr4}/{tr5}/{tr6}")
	public List<tollrecord> insertTollRecord(@PathVariable("id") String id,@PathVariable("t1") int t1,@PathVariable("t2") int t2,@PathVariable("t3") int t3,@PathVariable("t4") int t4,@PathVariable("t5") int t5,@PathVariable("t6") int t6,
			@PathVariable("tr1") int tr1,@PathVariable("tr2") int tr2,@PathVariable("tr3") int tr3,@PathVariable("tr4") int tr4,@PathVariable("tr5") int tr5,@PathVariable("tr6") int tr6)
	{
		tollrecord t=new tollrecord(id, t1, t2, t3, t4, t5, t6);
		this.tollrecordrep.save(t);
		tollrecord tr=new tollrecord("ret"+id, tr1, tr2, tr3, tr4, tr5, tr6);
		this.tollrecordrep.save(tr);
		return this.tollrecordrep.findAll();
	}
	
	@GetMapping("updatetollrecord/{id}/{t1}/{t2}/{t3}/{t4}/{t5}/{t6}")
	public List<tollrecord> updatetollrecord(@PathVariable("id") String id,@PathVariable("t1") int t1,@PathVariable("t2") int t2,@PathVariable("t3") int t3,@PathVariable("t4") int t4,@PathVariable("t5") int t5,@PathVariable("t6") int t6)
	{
		Optional<tollrecord> nu=this.tollrecordrep.findById(id);
		tollrecord su;
		if(nu.isPresent())
		{
			su=nu.get();
			su.setCode(id);
			su.setType1(t1);
			su.setType2(t2);
			su.setType3(t3);
			su.setType4(t4);
			su.setType5(t5);
			su.setType6(t6);

			this.tollrecordrep.save(su);
		}
//		this.numrep.
		return this.tollrecordrep.findAll();
	}
	
	@GetMapping("deletetollrecord/{code}")
	public List<tollrecord> deletetollrecord(@PathVariable("code") String id)
	{
		this.tollrecordrep.deleteById(id);

		this.tollrecordrep.deleteById("ret"+id);
		return this.tollrecordrep.findAll();
	}
	
	
	
	
	@GetMapping("get/{numplate}")
	public Optional<numberplate> getNumberPlateByNumber(@PathVariable("numplate") String nump)
	{
		return this.numrep.findById(nump);
	}
	
	public void updateToll(String nump,int toll) throws DocumentException, IOException
	{
		Optional<numberplate> nu=this.numrep.findById(nump);
		numberplate su;
		if(nu.isPresent())
		{
			su=nu.get();
			int t=su.getToll()+toll;
			su.setToll(t);
			this.numrep.save(su);
			if(toll<0)
			{
				toll*=-1;
				System.out.println(System.getProperty("java.class.path"));
				Context context = new Context();
		        numberplate k=nu.get();
		        context.setVariable("name",k.getName());
		        context.setVariable("lic",k.getLicense());
		        context.setVariable("nump",k.getId());
		        context.setVariable("mobile",k.getMobile());
		        context.setVariable("type",this.gettypeString(k.getType()));

		        context.setVariable("tollpaid",toll);

		        context.setVariable("dueamnt",k.getToll());
		        context.setVariable("date",new java.util.Date());


		        context.setVariable("msg", "the amount was paid");
		        
		        String htmlContentToRender = templateEngine.process("template", context);
		        String xHtml = xhtmlConvert(htmlContentToRender);

		        ITextRenderer renderer = new ITextRenderer();

		        String baseUrl = FileSystems
		                .getDefault()
		                .getPath("src", "main", "resources","templates")
		                .toUri()
		                .toURL()
		                .toString();
		        renderer.setDocumentFromString(xHtml, baseUrl);
		        renderer.layout();

		        java.io.OutputStream outputStream = new FileOutputStream("src/main/resources/temp_download/noc"+nu.get().getId()+".pdf");
		        renderer.createPDF(outputStream);
		        outputStream.close();


			}
			
			
			
		}
	}
	
	
	@RequestMapping("/getnocfile/{id}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) throws IOException, DocumentException {
		System.out.println("called");
		String fileName="noc"+id+".pdf";
		
		
		
		File file = new File("src/main/resources/temp_download/"+fileName);
		
		if(!file.exists())
		{
		
			this.createFile(id);
		
		}
		
		if (file.exists()) {

			//get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				//unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);


			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		
		}
	}

	
	
	@GetMapping("removetoll/{numberplate}/{toll}/{name}")
	public List<numberplate> addToll(@PathVariable("numberplate") String nump,@PathVariable("toll") int toll,@PathVariable("name") String name) throws DocumentException, IOException
	{
		int t=0-toll;
		this.updateToll(nump, t);
		return this.getallNumberPlatebyname(name);
	}
	
	@GetMapping("getallcardata/{numberplate}")
	public List<carrecord> getcardata(@PathVariable("numberplate") String nump)
	{
		List<carrecord> carrecords=this.carrecordrep.findAll();
		List<carrecord> carrecords2=new ArrayList<carrecord>();
		for(carrecord c:carrecords)
		{
			if(c.getNumberplate().contentEquals(nump))
				carrecords2.add(c);
		}
		return carrecords2;
	}
	public List<carrecord> getcardataparam(String nump,Date d,String co)
	{
		List<carrecord> carrecords=this.carrecordrep.findAll();
		List<carrecord> carrecords2=new ArrayList<carrecord>();
		System.out.println(nump+d+co);
		for(carrecord c:carrecords)
		{
			String d1=c.getDate().toString().substring(0,10);
			String d2=(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())).toString();
			System.out.println(d1+d2);
			if(c.getNumberplate().equals(nump) && d1.contentEquals(d2) && c.getTollcode().equals(co))
				carrecords2.add(c);
		}
		return carrecords2;
	}
	
	   public int gettoll(tollrecord t, int v)
	    {
	        if(v==1)
	            return t.getType1();
	        if(v==2)
	            return t.getType2();
	        if(v==3)
	            return t.getType3();
	        if(v==4)
	            return t.getType4();
	        if(v==5)
	            return t.getType5();
	            return t.getType6();
	    }
	   

	
	@GetMapping("addtoll/{numberplate}/{code}")
	public List<numberplate> addToll(@PathVariable("numberplate") String nump,@PathVariable("code") String code) throws DocumentException, IOException
	{
		String numco=nump.substring(0,Math.min(nump.length(),4));
		Optional<numberplate> num=this.getNumberPlateByNumber(nump);
		int typeval=num.get().getType();
		List<tolldata> tol=this.getTollData(code, numco);
		int toll=0;
		if(tol.size()>0)
		{
			toll=0;
		}
		else
		{
		List<carrecord> carrecords=this.getcardataparam(nump,new java.util.Date(), code);
		System.out.println(nump+code+" size"+carrecords.size());
		if(carrecords.size()>0)
		{
			Optional<tollrecord> tr=this.tollrecordrep.findById(code);
			Optional<tollrecord> tr2=this.tollrecordrep.findById("ret"+code);
			
			toll=this.gettoll(tr2.get(), typeval)-this.gettoll(tr.get(), typeval);
		}
		else
		{
			Optional<tollrecord> tr=this.tollrecordrep.findById(code);
			
			toll=this.gettoll(tr.get(), typeval);
		}
				
		}
		
		this.updateToll(nump, toll);
		carrecord c=new carrecord(nump, toll, new java.util.Date(), code);
		this.carrecordrep.save(c);
		return this.getallNumberPlate();
	}
	
	
	@Autowired
    SpringTemplateEngine templateEngine;
	
	@RequestMapping("/getdetails/{id}")
    public @ResponseBody numberplate savePDF(@PathVariable("id") String id) throws IOException, DocumentException {

		System.out.println(System.getProperty("java.class.path"));
		Context context = new Context();
        Optional<numberplate> s=this.numrep.findById(id);
        numberplate k=s.get();
        context.setVariable("name",k.getName());
        context.setVariable("marks",k.getLicense());

        
        String htmlContentToRender = templateEngine.process("template", context);
        String xHtml = xhtmlConvert(htmlContentToRender);

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources","templates")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        java.io.OutputStream outputStream = new FileOutputStream("src/main/resources/temp_download/test.pdf");
        renderer.createPDF(outputStream);
        outputStream.close();

        return k;

    }

    private String xhtmlConvert(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString("UTF-8");
    }


	
}
