<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
    
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title></head>
    
    <body>
      <div style="font-size: 25px; font-weight: bold; text-align: center; margin-top: 5px; margin-bottom: 10px;">商家信息</div>
      <div class="addWaiterTb">
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
                <input name="dishUrl" id="dishUrl" type="hidden" />
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
          <a href="javascript:void(0)" onclick="clearForm()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="margin-left: 20px; width: 60px;">取消</a>
          <a href="javascript:void(0)" onclick="submitForm()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-left: 30px; width: 60px;">确认</a></form>
      </div>
      <script>
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
              $("#add_dishType").dialog("close");
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
      </script>
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