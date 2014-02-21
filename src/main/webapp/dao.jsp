<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.blstream.myhoard.db.dao.CollectionDAO"%>
<%@page import="com.blstream.myhoard.db.model.CollectionDS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hibernate :: Test</title>
</head>
<body>
	
	<p> Test połączenia z bazą danych i dodawania/aktualizowania/usuwania rekordów w bazie. </p>
	
	<%
	
	// SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	// String dateInString = "31-08-1982 10:20:56";
	// Date date = sdf.parse(dateInString);
	
	// Date date = Timestamp.valueOf("2014-02-18 12:45:36");
	// CollectionDS collection = new CollectionDS("Monety", "Miliony monet", "monety 2014 SEźćńŋd", 42,
	//		 date, date, "Jan Bżćńółć");
	// CollectionDAO cd = new CollectionDAO();
	
	
	// cd.create(collection);
	// out.println("Dodano kolekcję o id = " + collection.getId() + "<br />");
	// collection.setDescription("Nowy opis kolekcji żśWgłh");
	// cd.update(collection);
	// out.println("Zaktualizowano kolekcję o id = " + collection.getId() + "<br />");
	
	
	// CollectionDS collection2 = new CollectionDS("Monety II", "kolekcja do usunięcia", "monety 2014 SEźćńŋd", 42,
	//		date, date, "Jan Bżćńółć");
	// cd.create(collection2);
	// cd.remove(collection2.getId());
	
	// cd.remove(collection2.getId());
	
	// CollectionDS collection3 = cd.get(collection2.getId());
	// CollectionDS collection4 = cd.get(collection.getId());
	
	// out.println("ID : " + collection4.getId() + "<br />");
	// out.println("NAME : " + collection4.getName() + "<br />");
	// out.println("DESCRIPTION : " + collection4.getDescription() + "<br />");
	// out.println("TAGS : " + collection4.getTags() + "<br />");
	// out.println("ITEMS_NUMBER : " + collection4.getItemsNumber() + "<br />");
	// out.println("CREATED_DATE : " + collection4.getCreatedDate() + "<br />");
	// out.println("MODIFIED_DATE : " + collection4.getModifiedDate() + "<br />");
	// out.println("OWNER : " + collection4.getOwner() + "<br />");
	
	
	// List<CollectionDS> list = cd.getList();
	
	// for(CollectionDS obj : list)
	//	out.println(obj.getId() + "<br />");
	
	
	%>
	
</body>
</html>
