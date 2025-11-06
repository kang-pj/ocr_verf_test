<#-- Select tag template -->
<select name="${name}" id="${id}" class="${class}">
    <#list options as option>
        <option value="${option.value}">${option.text}</option>
    </#list>
</select>