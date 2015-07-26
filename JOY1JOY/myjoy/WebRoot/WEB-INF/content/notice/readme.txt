存放公共的页面，如页头、页脚,公共js、css导入等等
【说明】
1.footer.jsp	页脚引用jsp
2.header_no_search.jsp	不带搜索框的页头引用jsp
3.header_with_search.jsp	带搜索框的页头引用jsp
4.header_topbar.jsp	这是页头中引用到的一个公共jsp，不在具体的页面中引用
5.base_import.jsp	公用css、js的导入jsp，目前只引入公用的css文件：style。css和jquery文件，其它要导入的css、js自行在页面中导入

上述1,2,3,5在具体的页面中会用到，引用方式参考:WEB-INF/content/activity/input.jsp文件