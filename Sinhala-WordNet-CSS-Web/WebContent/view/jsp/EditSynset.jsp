<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Synset</title>
<link rel="stylesheet" type="text/css" href="theme/css/editstyles.css">
<link rel="shortcut icon" href="images/wordnet1.jpg" />
<script type="text/javascript" src="theme/js/jscode.js"></script>
</head>
<body>
<div id="warp">
  <ul id="breadcrumbs">
    <li><a href="root.html">මූලය</a></li>
    <li><a href="noun.html">නාම පද</a></li>
    <li><a href="index.html">දෙය</a></li>
    <li><a href="ViewPhysicalEntity.html">භෞතිකව පවත්නා දෙය</a></li>
    <li><a href="viewObject.html">වස්තුව</a></li>
    <li><a href="viewLocation.html">පිහිටුම</a></li>
    <li><a href="viewRegion.html">කලාපය</a></li>
    <li><a href="viewRegion.html">ප්‍රදේශය</a></li>
    <li>පරිපාලන ප්‍රදේශය</li>
  </ul>
  <div style="float:left">
    <div class="margin20" style="float:left">
      <div class="whitebox">
        <h1>${synset.getWordsAsString()}</h1>
      </div>
      <div class="whitebox">
        <table>
          <tr>
            <td valign="top">ඉංග්‍රීසි අර්ථය:</td>
            <td valign="top">${synset.getDefinition()}</td>
          </tr>
          <tr>
            <td valign="top">ඉංග්‍රීසි උදාහරණ:</td>
            <td valign="top">${synset.getExample()}</td>
          </tr>
        </table>
      </div>
    </div>
    <div class="margin20" style="float:right">
      <div class="whitebox">
        <h1></h1>
      </div>
      <div class="whitebox">
        <table>
          <tr>
            <td valign="top">සිංහල අර්ථය:</td>
            <td valign="top"><input type="text" style="width:250px" value=""></td>
          </tr>
          <tr>
            <td valign="top">සිංහල උදාහරණ:</td>
            <td valign="top"><input type="text" style="width:250px" value=""></td>
          </tr>
        </table>
      </div>
    </div>
    </div>
  <div>
    <div id="left">
      <div class="margin20">
        <table width="372">
          <tr>
            <td>නව වචනය:</td>
            <td><input id="newword" type="text" class="box190" ></td>
            <td><button onclick="addword('newword','synsetlist');"> + එක්කරන්න </button></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td class="n"><select id="synsetlist" size="10" class="box190" multiple="multiple" onclick="updateattributes('synsetlist');">
              </select></td>
            <td valign="bottom"><button onclick="takefromlist('synsetlist');">-ඉවත්කරන්න</button></td>
          </tr>
        </table>
      </div>
      <div class="margin20">
        <table width="370">
          <tr>
            <td>ලිංග භේදය:</td>
            <td><input type="radio" name="gender" id="male" value="male">
              පුරුෂ&nbsp;&nbsp;
              <input type="radio" name="gender" id="female" value="female">
              ස්ත්‍රී&nbsp;&nbsp;
              <input type="radio" name="gender" id="neglect" value="neglect">
              නොසලකා හරින්න&nbsp;&nbsp; </td>
          </tr>
          <tr>
            <td>ප්‍රකෘතිය:</td>
            <td><input id="rootword" type="text" class="box190" ></td>
          </tr>
          <tr>
            <td>තද්දිතය:</td>
            <td><input id="thadditha" type="text" class="box190" ></td>
          </tr>
          <tr>
            <td>විරුද්ධ පදය:</td>
            <td><input id="antonym" type="text" class="box190" ></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="center"><button onclick="addattributes('synsetlist');"> යොමන්න</button></td>
          </tr>
        </table>
      </div>
      <div class="button_div">
                        <input type="button" value="යොමන්න" id="btn_add_synset" class="button"/>
                    </div>
    </div>
    
    <div id="suggestions">
      <div class="margin20">
        <center>
          <p>වචනවල පොදු අරුත්</p>
          <table width="238">
            <tr>
              <td width="125" valign="top"><p>${synset.getWordsAsString()} :</p></td>
              <td width="101" valign="top"><select id="list1" size="4" style="width: 100px"  multiple="multiple" ondblclick="add2list(&#39;list1&#39;,&#39;synsetlist&#39;);">
                  <c:forEach var="meaning" items="${intersection}">
                    <option>${meaning}</option>
                  </c:forEach>
                </select></td>
            </tr>
          </table>
        </center>
      </div>
      <div class="margin20">
        <center>
          <p>වචනවල අරුත්</p>
        </center>
        <center>
          <table width="502">
            <tr>
            <c:forEach begin="0" end="${wordList.size()-1}" varStatus="loop">
              <td width="48" valign="top"><p>${wordList.get(loop.index)} :</p></td>
              <td width="120" valign="top"><select id="list2" size="10" style="width: 120px"  multiple="multiple" ondblclick="add2list(&#39;list2&#39;,&#39;synsetlist&#39;);">
                  <c:forEach var="meaning" items="${meaningsList.get(loop.index)}">
                    <option>${meaning}</option>
                  </c:forEach>
                </select></td>
            </c:forEach>
            </tr>
          </table>        
        </center>
      </div>
    </div>
  </div>
  <div class="footer">
    <div class="links">
      <p class="lintitle1">© සියලුම හිමිකම් ඇවිරිණි. <a href="http://www.uom.lk/" target="_new" class="cse">සිංහල Wordnet ව්‍යාපෘතිය.</a> </p>
    </div>
  </div>
</div>
</body>
</html>
