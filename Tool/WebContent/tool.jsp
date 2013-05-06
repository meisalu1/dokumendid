<jsp:useBean id="tool" scope="request" type="controller.toolForm" />
<html>
<body bgcolor="white">
<a href='s'>servlet</a> | <a href='http://imbi.ld.ttu.ee/tomcat_webapp_logs/Tool/log_25_04.txt'>log.txt</a> <br>



Tööriistade nimekiri:
<br>




<form action='s?action=save' method=POST>
<input type='hidden' name='tool_id' value='<%=tool.getId() %>'>
<table>
<tr><td colspan=3 bgcolor=#CCCCCC>KIRJE MUUTMINE</td></tr>
<tr><td>Id:</td><td colspan=2><%=tool.getId() %></td></tr>
<tr><td>Tüüp:</td><td><input type='text' name='tool_type' value='<%=tool.getType() %>'></td><td><%=tool.getErrorByField("type")%></td></tr>
<tr><td>Kaal:</td><td><input type='text' name='tool_weight' value='<%=tool.getWeight() %>'></td><td><%=tool.getErrorByField("weight")%></td></tr>
<tr><td>Kirjeldus:</td><td><input type='text' name='tool_desc' value='<%=tool.getDescription() %>'></td></tr>
<tr><td colspan=2><input type=submit value="SALVESTA"></td></tr>
</table>
</form>
</body>
</html>