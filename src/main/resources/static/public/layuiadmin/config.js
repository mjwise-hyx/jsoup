/**

 @Name：layuiAdmin iframe版全局配置
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL（layui付费产品协议）

 */

layui.define(['laytpl', 'layer', 'element', 'util'], function (exports) {
    exports('setter', {
        container: 'LAY_app' //容器ID
        , base: layui.cache.base //记录静态资源所在路径
        , views: layui.cache.base + 'tpl/' //动态模板所在目录
        // , views: '/beam/views/' //动态模板所在目录
        , entry: '' //默认视图文件名
        , engine: '.html' //视图文件后缀名
        , pageTabs: true //是否开启页面选项卡功能。iframe版推荐开启

        , name: 'layuiAdmin'
        , tableName: 'layuiAdmin' //本地存储表名
        , MOD_NAME: 'admin' //模块事件名

        , debug: false //是否开启调试模式。如开启，接口异常时会抛出异常 URL 等信息

        //自定义请求字段
        , request: {
            tokenName: false //自动携带 token 的字段名（如：access_token）。可设置 false 不携带。
        }

        //自定义响应字段
        , response: {
            statusName: 'statusCode' //数据状态的字段名称
            , statusCode: {
                ok: 200 //数据状态一切正常的状态码
                , logout: 401 //登录状态失效的状态码
            }
            , msgName: 'errMsg' //状态信息的字段名称
            , dataName: 'data' //数据详情的字段名称
        }

        //扩展的第三方模块
        , extend: [
            'echarts', //echarts 核心包
            'echartsTheme' //echarts 主题
        ]

        //主题配置
        , theme: {
            //内置主题配色方案
            color: [{
                main: '#ffffff'
                ,logo: '#c490c0'
                ,selected: '#9ad7fc'
                ,header: '#8f81bc'
                ,alias: 'default'
            },{
                main: '#fff'
                ,logo: '#1f6fb5'
                ,header: '#82a6f5'
                ,selected: '#9ad7fc'
                ,alias: 'fashion-red' //时尚红
            },{
                main: '#fff'
                ,logo: '#8f81bc'
                ,selected: '#9ad7fc'
                ,header: '#8f81bc'
                ,alias: 'dark-blue' //藏蓝
            },{
                main: '#fff'
                ,logo:'#c490c0'
                ,header: '#c490c0'
                ,selected: '#9ad7fc'
                ,alias: 'purple-red' //紫红
            },{
                main: '#fff'
                ,logo: '#495A80'
                ,header: '#495A80'
                ,selected: '#9ad7fc'
                ,alias: 'green-header' //墨绿头
            },{
                main: '#fff'
                ,logo: '#b00404'
                ,selected: '#9ad7fc'
                ,header: '#003cbd'
                ,alias: 'ocean-header' //海洋头
            },{
                main: '#fff'
                ,logo: '#8a0411'
                ,selected: '#9ad7fc'
                ,header: '#009688'
                ,alias: 'green-header'
            },{
                main: '#fff'
                ,logo: '#8a0411'
                ,selected: '#9ad7fc'
                ,header: '#d7712b'
                ,alias: 'green-header'
            },{
                main: '#ffffff'
                ,logo: '#009688'
                ,selected: '#9ad7fc'
                ,header: '#009688'
                ,alias: 'green-header'
            },{
                main: '#ffffff'
                ,logo: '#Af3000'
                ,selected: '#9ad7fc'
                ,header: '#36b4ff'
                ,alias: 'green-header'
            },
            {
                main: '#F2F2F2'
                ,logo: '#65B8E3'
                ,selected: '#9ad7fc'
                ,header: '#7CCB70'
                ,alias: 'green-header'
            }]
            //初始的颜色索引，对应上面的配色方案数组索引
            //如果本地已经有主题色记录，则以本地记录为优先，除非请求本地数据（localStorage）
            , initColorIndex: 0
        }
    });
});
