<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
   <a href="javascript:void(0)"  id="import_index" class="easyui-linkbutton">导入商品到索引库</a>
</div>
<script type="text/javascript">
$(function(){
	$("#import_index").click(function(){
		$.post("/index/import",null, function(data){
			if(data.status == 200){
				$.messager.alert('提示','导入商品到索引库成功!');
			}else{
				$.messager.alert('提示','导入商品到索引库失败!');
			}
		});
	});
	
});
</script>