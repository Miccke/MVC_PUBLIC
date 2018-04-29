<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
    
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <link rel="stylesheet" type="text/css" href="../css/css.css">
      <link rel="stylesheet" type="text/css" href="../easyui/css/easyui.css">
      <link rel="stylesheet" type="text/css" href="../easyui/css/icon.css">
      <link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
      <script type="text/javascript" src="../easyui/js/jquery-1.8.3.min.js"></script>
      <script type="text/javascript" src="../../js/ajaxfileupload.js"></script>
      <!-- <script type="text/javascript" src="../easyui/js/jquery-1.9.1.js"></script> -->
      <script type="text/javascript" src="../easyui/js/jquery.easyui.min.js"></script>
      <script type="text/javascript">
      	var obj;
        $(function() {
          var flag; //判断新增还是修改
          obj = $('#tab').datagrid({
            title: '菜品列表',
            iconCls: 'icon-edit',
            //图标 
            //width : '400px',
            height: 'auto',
            nowrap: false,
            //当true的时候，会显示在一行里
            striped: true,
            //各行变色
            border: true,
            collapsible: false,
            //是否可折叠的 
            fit: true,
            //自动大小 
            url: '../../dishInfo/getAll',
            //sortName: 'code', 
            //sortOrder: 'desc', 
            remoteSort: false,
            loadMsg: '数据正在加载，请等待......',
            idField: 'dishId',
            singleSelect: true,
            //是否单选 
            pagination: true,
            //分页控件 
            rownumbers: true,
            //行号 
            checkOnSelect: true,
            selectOnCheck: true,
            //  remoteSort : false,
            //  sortName:'userName',
            //  sortOrder:'asc',
            rowStyler: function(index, record) {
              //console.info(index);
              //console.info(record);
              /*if(record.age > 5000){
             return "background:red";
             }*/
            },
            frozenColumns: [[
            //你把 ck 换成 QID 试试
            {
              field: 'QID',
              checkbox: true
            }]],
            columns: [[{
              field: 'dishId',
              title: 'dishId',
              hidden: true
            },
            {
              field: 'dishName',
              title: '菜品名称',
              width: '120px',
              align: 'center',
              sortable: true
            },
            {
              field: 'storeName',
              title: '餐厅名称',
              width: '120px',
              align: 'center',
              sortable: true
            },
            {
              field: 'dishtype',
              title: '菜品类型',
              width: '120px',
              align: 'center',
              sortable: true
            },
            {
              field: 'dishSpecification',
              title: '菜品规格',
              width: '100px',
              align: 'center',
              sortable: true
            },
            {
              field: 'dishMonthsales',
              title: '月销售量',
              width: '100px',
              align: 'center',
              sortable: true
            },
            {
              field: 'dishPrice',
              title: '菜品单价（元）',
              width: '150px',
              align: 'center',
              sortable: true
            },
            {
              field: 'dishStar',
              title: '菜品评分',
              width: '150px',
              align: 'center',
              sortable: true
            }]],

            toolbar: [{
              text: '添加',
              iconCls: 'icon-add',
              handler: function() {
                $('#add_dish').dialog({
                  left: "350px",
                  top: "100px",
                  width: 630,
                  height: 300,
                  cache: false,
                  shadow: false
                });
                $("#add_dish").dialog('open');

              }
            },
            '-', {
              text: "删除",
              iconCls: "icon-remove",
              handler: function() {
                //获取选中的行
                var row = obj.datagrid("getChecked");
                if (row != undefined) {
                  $.messager.confirm('信息确认', '你确定要删除选定的数据吗',
                  function(rs) {
                    if (rs) {
                      //发送请求到数据库删除
                      $.post("../../dishInfo/deleteDish", {
                        dishId: row[0].dishId
                      },
                      function(data) {
                        if (data > 0) {
                          $.messager.show({
                            title: '成功提示',
                            msg: '菜品删除成功',
                            timeout: 2000,
                            showType: 'slide'
                          });
                          rows = null;
                          obj.datagrid("reload"); //刷新表格
                        } else {
                          $.messager.show({
                            title: '失败提示',
                            msg: '菜品删除失败',
                            timeout: 2000,
                            showType: 'slide'
                          });
                        }
                      });
                    } else {
                      return;
                    }
                  });
                } else {
                  $.messager.show({
                    title: '温馨提示',
                    msg: '请选中您要删除的数据',
                    timeout: 2000,
                    showType: 'slide'
                  });
                }
              }
            },
            '-', {
              text: '查询',
              iconCls: 'icon-search',
              handler: function() {
                $("#lay").layout('expand', 'north');
              }
            }],
          });

          //设置分页控件 
          var p = $('#tab').datagrid('getPager');
          $(p).pagination({
            pageSize: 10,
            //每页显示的记录条数，默认为10 
            pageList: [5, 10, 15, 20, 50],
            //可以设置每页记录条数的列表 
            beforePageText: '第',
            //页数文本框前显示的汉字 
            afterPageText: '页 共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录 共 {total} 条记录',
            onBeforeRefresh: function() {
              $(this).pagination('loading');
              // alert('before refresh');
              $(this).pagination('loaded');
              $('#tab').datagrid('reload');
            }
          });

          $("#btn2").click(function() {
            $("#my_dialog").dialog('close');
          });

          $("#searchbtn").click(function() {
            $("#tab").datagrid('load', serializeForm($("#mysearch")));
          });

          $("#clearbtn").click(function() {
            $("#mysearch").form('clear');
            $("#tab").datagrid('load', {});
          });
          //正数验证
          function validateNumber(num) {
            var regNumber = /^[+]{0,1}(\d+)$/;
            if (!regNumber.test(num)) {
              alert("请输入整数数字");
              return false;
            } else {
              return true;
            }
          }

          function serializeForm(form) {
            var obj = {};
            $.each(form.serializeArray(),
            function(index) {
              if (obj[this['name']]) {
                obj[this['name']] = obj[this['name']] + ',' + this['value'];
              } else {
                obj[this['name']] = this['value'];
              }
            });
            return obj;
          }

        });
        </script>
    </head>
    
    <body>
      <div id="lay" class="easyui-layout" style="width: 100%; height: 100%;">
        <div region="north" title="用户查询" collapsed=true style="height: 60px;">
          <form id="mysearch" method="post">
          	菜品名称:
            <input name="dishName" class="easyui-validatebox" required="true" missingMessage="请输入菜品名称" value="" />
                        餐厅名称:
            <input name="storeName" class="easyui-validatebox" required="true" missingMessage="请输入餐厅名称" value="" />
            <a id="searchbtn" class="easyui-linkbutton">查询</a>
            <a id="clearbtn" class="easyui-linkbutton">清空</a></form>
        </div>
        <div region="center">
          <table id="tab"></table>
        </div>
      </div>
      <div id="my_dialog" title="新增用户" modal=true draggable=false class="easyui-dialog" closed=true style="width: 700px; height: 420px;">
        <div style="font-size: 25px; font-weight: bold; text-align: center; margin-top: 5px; margin-bottom: 10px;">商家信息</div>
        <div id="add_dish" class="easyui-dialog" title="添加菜品类型" data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true">
          <br/>
          <form action="" id="addDish" method="post" style="float: left; padding-left: 20px">
            <table border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td class="ftd">商家名称:</td>
                <td>
                  <input class="easyui-combobox" type="text" id="storeId" name="storeId"></input>
                </td>
                <td class="ftd" rowspan="4">餐厅logo:</td>
                <td rowspan="4">
                  <img src="" id="headImg" style="width: 130px; height: 120px;">
                  <span id="imgInput">
                    <input name="dishUrl" id="dishUrl" type="hidden" /></span>
                  <input name="file1" type="file" style="width: 65px;" size="20" id="fileUpload1" onchange="uploadFile(this,1)"></td>
              </tr>
              <tr>
                <td class="ftd">菜品种类:</td>
                <td>
                  <input class="easyui-combobox" type="text" id="dishTypeId" name="dishTypeId"></input>
                </td>
              </tr>
              <tr>
                <td class="ftd">菜品名称:</td>
                <td>
                  <input class="easyui-textbox" type="text" id="dishName" name="dishName" data-options="required:true"></input>
                </td>
              </tr>
              <tr>
                <td class="ftd">菜品规格:</td>
                <td>
                  <input class="easyui-textbox" type="text" id="dishSpecification" name="dishSpecification" data-options="required:true"></input>
                </td>
              </tr>
              <tr>
                <td class="ftd">单价:</td>
                <td>
                  <input class="easyui-numberbox" precision="2" id="dishPrice" name="dishPrice" type="text" data-options="required:true"></input>
                </td>
              </tr>
            </table>
            <br/>
            <br/>
            <a href="javascript:void(0)" onclick="clearForm()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="margin-left: 200px; width: 60px;">取消</a>
            <a href="javascript:void(0)" onclick="submitForm()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-left: 50px; width: 60px;">确认</a></form>
        </div>
      </div>
      <script type="text/javascript">
      	function submitForm() {
          $('#addDish').form('submit', {
            url: '../../dishInfo/insertDish',
            onSubmit: function() {
              var flag = $(this).form('validate');
              return flag
            },
            success: function(result) {
              $.messager.show({
                title: '温馨提示',
                msg: '菜品信息添加成功',
                timeout: 2000,
                showType: 'slide'
              });
              $('#addDish').form('clear');
              $("#add_dish").dialog("close");
              obj.datagrid("reload"); //刷新表格
            },
            error: function(result) {
              $.messager.alert("错误提示", "菜品信息添加失败\n", "error");
            }

          });
        }
        function clearForm() {
          $('#addDish').form('clear');
        }
      </script>
      <script type="text/javascript">
      	function uploadFile(obj, type) {
          var typeValue = 'headImg';
          $.ajaxFileUpload({
            url: "../../attaUpload",
            secureuri: false,
            // 一般设置为false  
            fileElementId: "fileUpload" + type,
            // 文件上传表单的id <input type="file" id="fileUpload" name="file" />  
            dataType: 'JSON',
            // 返回值类型 一般设置为json  
            data: {
              type: type
            },
            //{'type': type, "type2":2},  
            success: function(result) { // 服务器成功响应处理函数  
              var res = JSON.parse(result);
              var ids = "";

              if (res.length > 0) {
                var flag = false;
                for (var i = 0; i < res.length; i++) {
                  var name = res[i].name;
                  var id = res[i].id;
                  if (i == res.length - 1) {
                    ids += id;
                  } else {
                    ids += id + ",";
                  }
                  $("#headImg").attr("src", "../../imageShow?fileId=" + id);
                  flag = true;
                }
                //判断是上传头像还是附件
                var imageid = $("#dishUrl").val();
                if (imageid) {
                  //不为空，则拼接起来
                  $("#dishUrl").val(imageid + "," + ids);
                } else {
                  //为空，则直接赋值
                  $("#dishUrl").val(ids);
                }
              }
            },
            error: function(erre) { // 服务器响应失败处理函数  
              console.log("服务器异常");
            }
          });
          return false;
        }
        //附件移除操作
        function deletePic(obj, id) {
          //移除页面内容
          obj.parentElement.remove();
          //获取隐藏input的值
          var ids = $("#attachId").val();
          var idsStr = ids.split(",");
          //新建一个数据，用于接收未被移除的数据
          var idsStr2 = [];
          for (var i = 0; i < idsStr.length; i++) {
            if (idsStr[i] != id) {
              idsStr2.push(idsStr[i]);
            }
          }
          var ids2 = ""
          //遍历新数据，拼接id串
          for (var i = 0; i < idsStr2.length; i++) {
            if (i == idsStr2.length - 1) {
              ids2 += idsStr2[i];
            } else {
              ids2 += idsStr2[i] + ",";
            }
          }
          console.log(ids2);
          $("#attachId").val(ids2)
          //alert(id);
        }</script>
      <script type="text/javascript">
        $('#storeId').combobox({
          valueField: 'storeId',
          //值字段
          textField: 'storeName',
          //显示的字段
          url: '../../storeInfo/storlist',
          panelHeight: 'auto',
          required: true,
          editable: true,
          //不可编辑，只能选择
          value: '--请选择--',
          onChange: function(storeId) {
            //$('#city').combobox('clear');
            $('#dishTypeId').combobox({
              valueField: 'dishtypeId',
              //值字段
              textField: 'dishtype',
              //显示的字段
              url: '../../dishType/menulist?storeId=' + storeId,
              panelHeight: 'auto',
              required: true,
              editable: true,
              //不可编辑，只能选择
              value: '--请选择--'
            });
          }
        });
        </script>
    </body>
  </html>