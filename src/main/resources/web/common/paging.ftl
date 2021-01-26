<#macro paging pagingList url>
    <div class="row" style="margin-left: 0px">
        <nav aria-label="Page navigation">
            <span class="pagination" style="float: left" >
                当前第${pagingList.pageNum}页,共${pagingList.pages }页,共${pagingList.total }条记录
            </span>
            <ul class="pagination" style="float: right">
                <li>
                    <a onclick="window.location.href='${url!}&&pageNum=1'" href="#">首页</a>
                </li>
                <#if pagingList.hasPreviousPage>
                    <li>
                        <a onclick="window.location.href='${url!}&&pageNum=${pagingList.pageNum-1}'" aria-label="Previous" href="#">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </#if>
            <li>
                <#list pagingList.navigatepageNums as page_Nums>
                    <#if page_Nums == pagingList.pageNum >
                        <li class="active"><a href="#">${page_Nums}</a></li>
                    <#else >
                        <li>
                            <a onclick="window.location.href='${url!}&&pageNum=${page_Nums}'" href="#">${page_Nums}</a>
                        </li>
                    </#if>
                </#list>
                </li>
                <#if pagingList.hasNextPage>
                    <li>
                        <a onclick="window.location.href='${url!}&&pageNum=${pagingList.pageNum+1}'" aria-label="Next" href="#">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </#if>
                <li><a onclick="window.location.href='${url!}&&pageNum=${pagingList.pages}'" href="#">末页</a></li>
            </ul>
        </nav>
    </div>
</#macro>