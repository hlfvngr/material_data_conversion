layui.use('table', function () {
    var table = layui.table;
    var util = layui.util;
    var winWight = document.body.clientWidth;
    //第一个实例
    table.render({
        elem: '#logInfo'
        , height: 500
        , url: '/api/v1/import/index' //数据接口
        , page: true, //开启分页
        method:'POST',
        cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
        limit: 10,//每页显示的记录数量
        limits: [5, 8, 10, 15, 20],//可选择每页显示的数量
        cols: [[ //表头
            {field: 'roadName', title: '路段名称',  fixed: 'left'}
            , {field: 'stationName', title: '收费站'}
            , {
                field: 'result', title: '导入状态',  templet: function (data) {
                    if (data.Result == 0) {
                        return "<span>导入成功</span>";
                    } else {
                        return "<span>导入失败</span>";
                    }
                }
            }
            , {field: 'importTime', title: '导入时间',
                templet: "<div>{{layui.util.toDateString(d.importTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"
            }
        ]]
    });

});