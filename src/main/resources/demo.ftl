<table style='width:100.0%;border:1px solid #e5e5e5;margin-bottom:15px;border-collapse:collapse;font-size:15px'>
    <th style='width:40px;border:1px solid #e5e5e5;background-color:#f5f5f5;padding:5px;text-align:center;'>序号</th>
    <th style='width:150px;border:1px solid #e5e5e5;background-color:#f5f5f5;padding:5px;text-align:center;'>姓名</th>
    <#list dataList as item>
        <tr>
            <td style='padding:5px;text-align:center;border:1px solid #e5e5e5;'>${item.number}</td>
            <td style='padding:5px;text-align:center;border:1px solid #e5e5e5;'>${item.name}</td>
        </tr>
    </#list>
</table>