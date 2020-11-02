layui.use(['tree', 'util'], function () {
    var tree = layui.tree
        , layer = layui.layer
        , util = layui.util
    //模拟数据1
    data1 = [{
        title: '导入'
        , id: 1
        , children: [{
            title: '合计工班'
            , id: 1000
            ,
        }, {
            title: '自然日工班'
            , id: 1001
        }]
    }];
    //无连接线风格
    tree.render({
        elem: '#test1'
        , data: data1
        , showLine: false  //是否开启连接线
    });
})