<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:dt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>Administrator</Author>
  <LastAuthor>微软用户</LastAuthor>
  <Created>2013-06-22T14:13:40Z</Created>
  <Version>14.00</Version>
 </DocumentProperties>
 <CustomDocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <KSOProductBuildVer dt:dt="string">2052-8.1.0.3526</KSOProductBuildVer>
 </CustomDocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <Colors>
   <Color>
    <Index>3</Index>
    <RGB>#92D050</RGB>
   </Color>
   <Color>
    <Index>10</Index>
    <RGB>#17375D</RGB>
   </Color>
   <Color>
    <Index>14</Index>
    <RGB>#F2F2F2</RGB>
   </Color>
  </Colors>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>4845</WindowHeight>
  <WindowWidth>6030</WindowWidth>
  <WindowTopX>120</WindowTopX>
  <WindowTopY>135</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Bottom"/>
   <Borders/>
   <Font ss:FontName="Arial" x:Family="Swiss"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="m96472576">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="微软雅黑" x:CharSet="134" x:Family="Swiss" ss:Size="16"
    ss:Color="#000000"/>
  </Style>
  <Style ss:ID="m96472596">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="微软雅黑" x:CharSet="134" x:Family="Swiss" ss:Size="16"
    ss:Color="#000000"/>
  </Style>
  <Style ss:ID="m96472616">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="微软雅黑" x:CharSet="134" x:Family="Swiss" ss:Size="16"
    ss:Color="#000000"/>
  </Style>
  <Style ss:ID="m96472636">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="微软雅黑" x:CharSet="134" x:Family="Swiss" ss:Size="16"
    ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s68">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center" ss:WrapText="1"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="微软雅黑" x:CharSet="134" x:Family="Swiss" ss:Size="11"
    ss:Color="#FFFFFF" ss:Bold="1"/>
   <Interior ss:Color="#17375D" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="s69">
   <Font ss:FontName="宋体" x:CharSet="134"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="Default">
  <Table ss:ExpandedColumnCount="2" ss:ExpandedRowCount="12" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54">
   <Column ss:AutoFitWidth="0" ss:Width="197.25"/>
   <Column ss:AutoFitWidth="0" ss:Width="183.75"/>
   <Row ss:AutoFitHeight="0" ss:Height="29.25">
    <Cell ss:MergeAcross="1" ss:StyleID="m96472576"><Data ss:Type="String">告警类型统计周报</Data></Cell>
   </Row>
   <Row ss:AutoFitHeight="0" ss:Height="15">
    <Cell ss:StyleID="s68"><Data ss:Type="String">告警类型</Data></Cell>
    <Cell ss:StyleID="s68"><Data ss:Type="String">告警数量</Data></Cell>
   </Row>
<#list report12Table1?sort_by("count")?reverse as table1>	
   <Row>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.type}</Data></Cell>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.count}</Data></Cell>
   </Row>
</#list> 
   <Row ss:Height="22.5">
    <Cell ss:MergeAcross="1" ss:StyleID="m96472596"><Data ss:Type="String">告警等级统计周报</Data></Cell>
   </Row>
   <Row ss:Height="15">
    <Cell ss:StyleID="s68"><Data ss:Type="String">告警等级</Data></Cell>
    <Cell ss:StyleID="s68"><Data ss:Type="String">告警数量</Data></Cell>
   </Row>
<#list report12Table2?sort_by("count")?reverse as table1>	
   <Row>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.rank}</Data></Cell>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.count}</Data></Cell>
   </Row>
</#list>
   <Row ss:Height="22.5">
    <Cell ss:MergeAcross="1" ss:StyleID="m96472616"><Data ss:Type="String">严重警告次数最多的10个设备</Data></Cell>
   </Row>
   <Row ss:Height="15">
    <Cell ss:StyleID="s68"><Data ss:Type="String">设备名称</Data></Cell>
    <Cell ss:StyleID="s68"><Data ss:Type="String">告警数量</Data></Cell>
   </Row>
<#list report12Table3?sort_by("count")?reverse as table1>	
   <Row>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.name}</Data></Cell>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.count}</Data></Cell>
   </Row>
 </#list>
   <Row ss:Height="22.5">
    <Cell ss:MergeAcross="1" ss:StyleID="m96472636"><Data ss:Type="String">总告警次数最多的10个设备</Data></Cell>
   </Row>

   <Row ss:Height="15">
    <Cell ss:StyleID="s68"><Data ss:Type="String">设备名称</Data></Cell>
    <Cell ss:StyleID="s68"><Data ss:Type="String">告警数量</Data></Cell>
   </Row>
<#list report12Table4?sort_by("count")?reverse as table1>	
   <Row>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.name}</Data></Cell>
    <Cell ss:StyleID="s69"><Data ss:Type="String">${table1.count}</Data></Cell>
   </Row>
</#list>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <Print>
    <ValidPrinterInfo/>
    <HorizontalResolution>3000</HorizontalResolution>
    <VerticalResolution>3000</VerticalResolution>
   </Print>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>16</ActiveRow>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
