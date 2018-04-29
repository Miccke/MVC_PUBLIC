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
            title: '商家用户列表',
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
            url: '../../storeInfo/storlist',
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
              field: 'storeId',
              title: 'storeId',
              hidden: true
            },
            {
              field: 'storeName',
              title: '餐厅名称',
              width: '120px',
              align: 'center',
              sortable: true
            },
            {
              field: 'storeUrl',
              title: '餐厅logo',
              width: '100px',
              align: 'center',
              formatter: function(value, row) {
                var str = "";
                if (value != "" || value != null) {
                  str = '<img style="height: 80px;width: 80px" src="../../imageShow?fileId=' + value + '"/>';
                  return str;
                }
              }
            },
            {
              field: 'storePhone',
              title: '手机号',
              width: '150px',
              align: 'center',
              sortable: true
            },
            {
              field: 'storeAddress',
              title: '餐厅地址',
              width: '150px',
              align: 'center',
              sortable: true
            },
            {
              field: 'reviewStar',
              title: '评分',
              width: '50px',
              align: 'center',
              sortable: true
            },
            {
              field: 'salesVolume',
              title: '月销量',
              width: '90px',
              align: 'center',
              sortable: true
            },
            {
              field: 'minimum',
              title: '起送价',
              width: '90px',
              align: 'center',
              sortable: true
            },
            {
              field: 'deliveryCost',
              title: '配送费',
              width: '50px',
              align: 'center',
              sortable: true
            },
            {
              field: 'legalPerson',
              title: '商家法人',
              width: '90px',
              align: 'center',
              sortable: true
            },
            {
              field: 'openingStart',
              title: '营业时间',
              width: '150px',
              align: 'center',
              formatter: function(value, row, index) {
                function getDateStr(date) {
                  if (date != null && date != "") {
                    var y = date.getFullYear();
                    var M = date.getMonth() + 1;
                    var d = date.getDate();
                    var h = date.getHours();
                    var m = date.getMinutes();
                    var s = date.getSeconds();
                    if (h < 10) {
                      h = '0' + h;
                    }
                    if (m < 10) {
                      m = '0' + m;
                    }
                    if (s < 10) {
                      s = '0' + s;
                    }
                    var dateStr = h + ':' + m + ':' + s;

                    return dateStr;
                  } else {
                    return null;
                  }
                }

                var start = new Date(row.openingStart);
                var end = new Date(row.openingEnd);

                var startTime = getDateStr(start);
                var endTime = getDateStr(end);

                return startTime + "至" + endTime;
              }
            }]],

            toolbar: [{
              text: '添加',
              iconCls: 'icon-add',
              handler: function() {
                flag = 'add';
                $("#my_dialog").dialog({
                  title: "新增商家用户",
                  width: 700,
                  height: 470,
                  //显示效果中拖动且缩短了高度
                  left: 400,
                  top: 10,
                  closed: false,
                  cache: false,
                  //title:"对话框1",
                  resizable: true,
                  //可缩放 
                  href: 'addStore.jsp',
                  onClose: function() {
                    //alert("关闭了");
                  },
                  buttons: [{
                    text: '保存',
                    handler: function() {
                      var data = {};
                      var formArray = $("#storeForm").serializeArray();
                      $.each(formArray,
                      function() {
                        data[this.name] = this.value;

                        console.log(data)
                      });
                      $.ajax({
                        url: "../../storeInfo/insertStore",
                        data: {
                          "dataObject": JSON.stringify(data)
                        },
                        type: 'post',
                        async: true,
                        success: function(result) {
                          $.messager.show({
                            title: '温馨提示',
                            msg: '商家信息添加成功',
                            timeout: 2000,
                            showType: 'slide'
                          });
                          $("#my_dialog").dialog("close");
                          obj.datagrid("reload"); //刷新表格
                        },
                        error: function(result) {
                          $.messager.alert("错误提示", "商家信息添加失败\n", "error");
                        }
                      });
                    }
                  },
                  {
                    text: '关闭',
                    handler: function() {
                      //关闭本对话框
                      $("#my_dialog").dialog("close");
                    }
                  }]
                });
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
                      $.post("../../storeInfo/deleteStore", {
                        storeId: row[0].storeId
                      },
                      function(data) {
                        if (data > 0) {
                          $.messager.show({
                            title: '成功提示',
                            msg: '商家删除成功',
                            timeout: 2000,
                            showType: 'slide'
                          });
                          rows = null;
                          obj.datagrid("reload"); //刷新表格
                        } else {
                          $.messager.show({
                            title: '失败提示',
                            msg: '商家删除失败',
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
    </head>
    
    <body>
      <div id="lay" class="easyui-layout" style="width: 100%; height: 100%;">
        <div region="center">
          <table id="tab"></table>
        </div>
      </div>
      <div id="my_dialog" title="新增商家用户" modal=true draggable=false class="easyui-dialog" closed=true style="width: 700px; height: 400px;"></div>
    </body>
  </html>