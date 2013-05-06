<%@ page import="model.Tool" %>
<jsp:useBean id="tools" scope="request" type="model.Tool[]" />
<html>
<body bgcolor="white">
<a href='s'>servlet</a> | <a href='http://imbi.ld.ttu.ee/tomcat_webapp_logs/Tool/log_25_04.txt'>log.txt</a> <br>
Tööriistade nimekiri:
<br>
<%
String id = "" ;
String type = "" ;
String weight = "" ;
out.println("<table bgcolor='#000000' border=0 cellpadding=0 cellspacing=0><tr><td><table width=100% border=0cellpadding=2 cellspacing=1>");
out.println("<tr bgcolor='#cccccc'><td><STRONG>kood&nbsp;</td><td><STRONG>tüüp&nbsp;</td><td><STRONG>kaal&nbsp;</td><td>......</td><td>......</td></tr><tr></tr>");
try
{
for (int n = 0; n < tools.length ; n++)
{
id = Integer.toString(tools[n].getId()) ;
type = tools[n].getType() ;
weight = Integer.toString(tools[n].getWeight()) ;
int m=1+n;
out.println("<TR BGCOLOR='#FFFFFF' ><TD nowrap>");
out.println("&nbsp;"+ id + "&nbsp;</TD><TD>"+ type + "&nbsp;</TD><TD>"+ weight + "&nbsp;");
out.println("</TD><TD align='top' nowrap><a HREF='javascript:get_plane("+ m +")' TARGET='_self'><b><u>kirjeldus</u><b></a></TD>");
out.println("</TD><TD align='top' nowrap><a HREF='s?id=" + id + "'TARGET='_self'><b><u>muuda</u><b></a></TD></TR>");
}
}
catch(Exception ex)
{
out.println("Mingi viga" + ex.getMessage());
}
out.println("</table></td></tr></table>");
out.println("</form>");
%>

<br>
<br> 
<div ID="ajax_response">
</div>
<div ID="description_form" style="visibility:hidden;">
<form name=description_form>
<TABLE BGCOLOR='#cccccc'>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#eeeeee' COLSPAN=2>Kirjeldus</TD></tr>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>id</td><td BGCOLOR='#cccccc'><input type=text name=plane_id size=4 disabled></TD></tr>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>kirjeldus:</td><td BGCOLOR='#cccccc'><textarea name=description cols=25 rows=5></textarea></TD></tr>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap COLSPAN=2><input type="button" value="Sulge" onClick="hide_description_form()"></TD></tr>
</TABLE>
</form>
</div>

<script src="static/js/toolservice.js"></script>
</body>
</html>