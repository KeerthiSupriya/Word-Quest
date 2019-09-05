<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="searchEngine.FolderSearch"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
		<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Search Algo</title>
		
		<style>
			.tableClass{
			    border-collapse: collapse;
			}
			
			.tableChildClass {
			    border: 1px solid black;
			}
		</style> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
img {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
body, html {
  height: 100%;
  width:100%;
}
label
{
  font-size:80px;
  font-type:Magneto;
  font-color: white;
  text-align:center;
}

.bgb
{
background-color:black;
}

.bg { 
  /* The image used */
  /* Full height */
  height: 100%; 
  background-image:url("https://store-images.microsoft.com/image/apps.14428.9007199266244940.85ce6d44-74ff-42ea-9304-7753adf175a3.89afdc71-34a7-43e5-aa4e-a18e2c2f3a2f?mode=scale&q=90&h=600&w=1200&background=%230b1923");

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
.btn
{
 align: center;
 background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;

}
.form
{
font-size:24px;
border: 0px solid blue;
}
.tble
{
border: 0px solid black;
}
table,th,td{
  border: 1px solid black;
  width:700px;
}
tr td input[type=text] {
 height:100%;
  width: 100%;
  box-sizing: border-box;
  -webkit-box-sizing:border-box;
  -moz-box-sizing: border-box;
  background:transparent !important;
  border: 0px;
}
center{
align: center;
}

</style>


</head>


	<body class="bgb" >
	<img src="images/Logo.png" alt="Search Engine Logo" class="center" />
	<img src="images/keyword-mapping-e1535461783696.jpg" alt="Search Engine Logo" class="center" />
	<br></br>

		<form action="index.jsp" method="post" class="form">
		<center>
			<table bgcolor="#FFFFFF"  width="700px">
				<tr>
					<td align="center">
						Enter a word you like to search
					</td>
					<td>
					
					    <!-- <input  type="text" id="keyword" name="keyword" class="gbqfif" disabled="" autocomplete="off" aria-hidden="true" style="border: medium none; padding: 0px; margin: 0px; height: auto; width: 100%; position: absolute; z-index: 1; background-color: transparent; color: silver; transition: all 0.218s ease 0s; opacity: 0; left: 0px; text-align: left;" id="gs_htif0" dir="ltr"> -->
					
					
						<input type="text" id="keyword" name="keyword" />
					</td>
				</tr>
				<tr>
					<td align="center">
						Number of files to be searched
					</td>
					<td>
						<select class="center" id="topNRecords" name="topNRecords">
							<option selected="selected" value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
							<option value="60">60</option>
							<option value="70">70</option>
							<option value="80">80</option>
							<option value="90">90</option>
							<option value="100">100</option>

						</select>
					</td>
				</tr>
				</table>
				<br></br>
						<input type="submit"  class="btn" value="Search"  />
		</form>
		
		<div  class="bgb">
			<%
				if(
						request.getParameter("keyword")!=null && !request.getParameter("keyword").equals("")
						&& request.getParameter("topNRecords")!=null && !request.getParameter("topNRecords").equals("")
						){
					List<Entry<String, Integer>> topNSortedFileContentCount = new FolderSearch("C:\\Users\\TEKWISSEN Group-13\\eclipse-workspace\\SearchEngine\\src\\webpages").search(request.getParameter("keyword"), Integer.parseInt(request.getParameter("topNRecords")));
					//System.out.println(topNSortedFileContentCount.size());
					if(topNSortedFileContentCount!=null && topNSortedFileContentCount.size()!=0){
						out.write("<hr>");
						out.write("Output:<br>");
						out.write("<table bgcolor=#FFFFFF class='tableClass' width:700px>");
						out.write("<tr>");
						out.write("<th class='tableChildClass'>File Name</th>");
						out.write("<th class='tableChildClass'>Count</th>");
						out.write("</tr>");
						for(Entry<String, Integer> entry : topNSortedFileContentCount){
							out.write("<tr>");
							out.write("<td align='center' class='tableChildClass'>" + entry.getKey() + "</td>");
							out.write("<td align='center' class='tableChildClass'>" + entry.getValue() + "</td>");
							out.write("</tr>");
						}
						out.write("</table>");
					}
					else{
						//System.out.println("suggest started");
						List<String> SuggestedList=new FolderSearch("C:\\Users\\TEKWISSEN Group-13\\eclipse-workspace\\SearchEngine\\src\\webpages").suggest(request.getParameter("keyword"));
						out.write("<hr>");
						out.write("Output:<br>");
						out.write("<table bgcolor=#FFFFFF class='tableClass'>");
						out.write("<tr>");
						out.write("<th class='tableChildClass'>Suggested Words</th>");
						out.write("</tr>");
						for(String word:SuggestedList){
							out.write("<tr>");
							out.write("<td class='tableChildClass'>" + word + "</td>");
							out.write("</tr>");
						}
						out.write("</table>");
					}
				}
			%>
		</div>



</body></html>