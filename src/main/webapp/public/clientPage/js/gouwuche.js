function $(_id) {
		return document.getElementById(_id);
	}
	function add_gouwuche() {
		var obj = document.getElementsByClassName('caiming_name');
		for (var i = 0; i < obj.length; i++) {
			obj[i].onclick = function() {
				//检查表格中是否已存在当前点击的菜品的记录
				var o = document.getElementsByTagName('tbody')[0]
						.getElementsByTagName('td');
				for (var j = 0; j < o.length; j++)
					if (o[j].getAttribute('code') == this.getAttribute('code'))
						return false;

				//向tbody添加记录
				var oTbody = document.getElementsByTagName('tbody')[0];
				var newRow = oTbody.insertRow();
				newRow.setAttribute("id",this.getAttribute('id')) ;
				
				var cell_1 = newRow.insertCell(0);
				cell_1.setAttribute('code', this.getAttribute('code'));
				cell_1.innerHTML = this.getAttribute('code');

				var cell_2 = newRow.insertCell(1);
				cell_2.setAttribute('price', this.getAttribute('price'));
				cell_2.innerHTML = '<span class="j_ge">￥'
						+ this.getAttribute('price') + '</span>';

				var cell_3 = newRow.insertCell(2);
				cell_3.innerHTML = '<span type="button" class="decrease"><span  id="min" class="iconfont icon-jianshao"></span></span>'
						+ '<span class="text_box"/>1</span>'
						+ '<span type="button" class="increase"><span id="add" class="iconfont icon-tubiao225"></span></span>';
				//绑定按钮事件
				var oButton = document.getElementsByTagName('span');
				for (var k = 0; k < oButton.length; k++) {
					if (oButton[k].className == 'increase') {
						oButton[k].onclick = increase;
					}
					;
					if (oButton[k].className == 'decrease') {
						oButton[k].onclick = decrease;
					}
					;
				}
				//计算总价
				calcTotalPrice();
				sum();
			}
		}
	}

	function calcTotalPrice() {
		var obj = document.getElementsByTagName('tbody')[0]
				.getElementsByTagName('tr'), sum = 0;
		for (var i = 0; i < obj.length; i++) {
			var o = obj[i].getElementsByTagName('td');
			sum += parseFloat(o[1].getAttribute('price'))
					* parseInt(o[2].getElementsByClassName('text_box')[0].innerText);
		}
		document.getElementsByClassName('text-left')[0]
				.getElementsByTagName('span')[0].innerHTML = sum.toFixed(2);
	}

	function increase() {
		var oNumber = this.parentNode.parentNode.getElementsByTagName('td')[2]
				.getElementsByClassName('text_box')[0].innerText;
		this.parentNode.parentNode.getElementsByTagName('td')[2]
				.getElementsByClassName('text_box')[0].innerText = parseInt((parseInt(oNumber)) + 1);
		calcTotalPrice();
		sum();
	}

	function decrease() {
		var oNumber = this.parentNode.parentNode.getElementsByTagName('td')[2]
				.getElementsByClassName('text_box')[0].innerText;
		//只有一份时点击减少按钮，删除该行记录
		if ((parseInt(oNumber)) == 1) {
			this.parentNode.parentNode.parentNode
					.removeChild(this.parentNode.parentNode);
		} else
			this.parentNode.parentNode.getElementsByTagName('td')[2]
					.getElementsByClassName('text_box')[0].innerText = parseInt((parseInt(oNumber)) - 1);
		calcTotalPrice();
		sum();
	}

	function sum() {
		var obj = document.getElementsByTagName('tbody')[0]
				.getElementsByTagName('tr'), sum = 0;
		for (var i = 0; i < obj.length; i++) {
			var o = obj[i].getElementsByTagName('td');
			sum += parseInt(o[2].getElementsByClassName('text_box')[0].innerText);
		}
		document.getElementsByClassName('footer')[0]
				.getElementsByTagName('span')[0].getElementsByTagName('i')[0].innerHTML = sum;
	}