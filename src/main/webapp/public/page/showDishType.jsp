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
        var editRow = undefined; //记录正在被编辑的行
        $(function() {
          var flag; //判断新增还是修改
          obj = $('#tab').datagrid({
            title: '菜品类型列表',
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
            url: '../../dishType/getAllByPage',
            //sortName: 'code', 
            //sortOrder: 'desc', 
            remoteSort: false,
            loadMsg: '数据正在加载，请等待......',
            idField: 'id',
            singleSelect: true,
            //是否单选 
            pagination: true,
            //分页控件 
            rownumbers: true,
            //行号 
            checkOnSelect: true,
            selectOnCheck: true,
            //	remoteSort : false,
            //	sortName:'userName',
            //	sortOrder:'asc',
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
              field: 'dishtypeId',
              title: 'dishtypeId',
              hidden: true
            },
            {
              field: 'storeName',
              title: '餐厅名称',
              width: 150,
              align: 'center',
              editor: {
                type: "text",
                options: {
                  requires: true
                }
              }
            },
            {
              field: 'dishtype',
              title: '菜品种类',
              width: 100,
              align: 'center',
              editor: {
                type: "text",
                options: {
                  requires: true
                }
              }
            },
            ]],

            toolbar: [{
              text: '添加',
              iconCls: 'icon-add',
              handler: function() {
                $('#add_dishType').dialog({
                  left: "450px",
                  top: "100px",
                  width: 260,
                  height: 170,
                  cache: false,
                  shadow: false
                });
                $("#add_dishType").dialog('open');

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
                      $.post("../../dishType/deleteDishType", {
                        dishtypeId: row[0].dishtypeId
                      },
                      function(data) {
                        if (data > 0) {
                          $.messager.show({
                            title: '成功提示',
                            msg: '菜品类型删除成功',
                            timeout: 2000,
                            showType: 'slide'
                          });
                          rows = null;
                          obj.datagrid("reload"); //刷新表格
                        } else {
                          $.messager.show({
                            title: '失败提示',
                            msg: '菜品类型删除失败',
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
        });
      </script>
      <script>function submitForm() {
          $('#addStoreForm').form('submit', {
            url: '../../dishType/insertDishType',
            onSubmit: function() {
              var flag = $(this).form('validate');
              return flag
            },
            success: function(result) {
              $.messager.show({
                title: '温馨提示',
                msg: '菜品类型添加成功',
                timeout: 2000,
                showType: 'slide'
              });
              $('#addStoreForm').form('clear'); //清控编辑的数据
              $("#add_dishType").dialog("close");
              obj.datagrid("reload"); //刷新表格
            },
            error: function(result) {
              $.messager.alert("错误提示", "菜品类型添加失败\n", "error");
            }

          });
        }
        function clearForm() {
          $('#addStoreForm').form('clear');
        }
    </script>
    </head>
    
    <body>
      <div id="lay" class="easyui-layout" style="width: 100%; height: 100%;">
        <div region="center">
          <table id="tab"></table>
        </div>
      </div>
      <div id="my_dialog" title="添加菜品类型" modal=true draggable=false class="easyui-dialog" closed=true style="width: 700px; height: 400px;">
        <div id="add_dishType" class="easyui-dialog" title="添加菜品类型" data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true">
          <br />
          <form action="" id="addStoreForm" method="post" style="float: left; padding-left: 20px">
            <p>
              <span>餐厅名称:</span>
              <input class="easyui-combobox" valuefield="storeId" textfield="storeName" url="../../storeInfo/storlist" panelheight="auto" style="width: 140px;" required="true" name="storeId"></input>
            </p>
            <p>
              <span>菜品种类:</span>
              <input type="text" id="dishtype" name="dishtype" data-options="required:true" style="width: 140px;"></input>
            </p>
            <a href="javascript:void(0)" onclick="clearForm()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="margin-left: 20px; width: 60px;">取消</a>
            <a href="javascript:void(0)" onclick="submitForm()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-left: 30px; width: 60px;">确认</a></form>
        </div>
      </div>
    </body>
  </html>