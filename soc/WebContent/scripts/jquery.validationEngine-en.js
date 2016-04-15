
(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
                "required": { // Add your regex rules here, you can take telephone as an example
                    "regex": "none",
                    "alertText": "* 此项不能为空...",
                    "alertTextCheckboxMultiple": "* 请至少选择一项",
                    "alertTextCheckboxe": "* This checkbox is required"
                },
                "minSize": {
                    "regex": "none",
                    "alertText": "* 最小长度不能小于 ",
                    "alertText2": " 请重新输入"
                },
                "maxSize": {
                    "regex": "none",
                    "alertText": "* 最大长度不能大于 ",
                    "alertText2": " 请重新输入"
                },
                "min": {
                    "regex": "none",
                    "alertText": "* 最小值不能小于 "
                },
                "max": {
                    "regex": "none",
                    "alertText": "* 最大值不能大于 "
                },
                "past": {
                    "regex": "none",
                    "alertText": "* Date prior to "
                },
                "future": {
                    "regex": "none",
                    "alertText": "* Date past "
                },	
                "maxCheckbox": {
                    "regex": "none",
                    "alertText": "* Checks allowed Exceeded"
                },
                "minCheckbox": {
                    "regex": "none",
                    "alertText": "* Please select ",
                    "alertText2": " options"
                },
                "equals": {
                    "regex": "none",
                    "alertText": "* 确认密码和密码不一致..."
                },
                "phone": {
                    // credit: jquery.h5validate.js / orefalo
                    //"regex": /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
                	"regex": /^(130|131|132|133|134|135|136|137|138|139|15[0-9]|189|186|187|170|188|185)\d{8}$/,
                    "alertText": "* 请输入正确的手机号码..."
                },
                "email": {
                    // Simplified, was not working in the Iphone browser
                    "regex": /^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/,
                    "alertText": "* 请输入正确的邮箱地址..."
                },
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "* 请输入整数..."
                },
                "number": {
                    // Number, including positive, negative, and floating decimal. credit: orefalo
                    "regex": /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
                    "alertText": "* Invalid floating decimal number"
                },
                "spechars": {
                	"regex": /^([^`~!@#$%^&*()=|{}':;, \\\[\]<>\/?~！@#￥……&*（）—|{}【】‘；：”“'\"，、？])*$/,
                	"alertText": "* 您输入的内容含有特殊字符。。。"
                },
                "date": {
                    // Date in ISO format. Credit: bassistance
                    "regex": /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/,
                    "alertText": "* Invalid date, must be in YYYY-MM-DD format"
                },
                "ipv4": {
                    "regex": /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
                    "alertText": "* 请输入合法的IP地址..."
                },
                "url": {
                    "regex": /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/,
                    "alertText": "* Invalid URL"
                },
                "onlyNumberSp": {
                    "regex": /^[0-9\ ]+$/,
                    "alertText": "* 请输入数字..."
                },
                "Talphone": {
                	"regex": /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
                    "alertText": "* 请输入正确的固定电话..."
                },
                "onlyLetterSp": {
                    "regex": /^[a-zA-Z\ \']+$/,
                    "alertText": "* Letters only"
                },
                "onlyLetterNumber": {
                   // "regex": /^[0-9a-zA-Z]+$/,
                    //"alertText": "* 请不要输入特殊字符..."
                },
                // --- CUSTOM RULES -- Those are specific to the demos, they can be removed or changed to your likings
                "ajaxUserCall": {
                    "url": "ajaxValidateFieldUser",
                    // you may want to pass extra data on the ajax call
                    "extraData": "name=eric",
                    "alertText": "* This user is already taken",
                    "alertTextLoad": "* Validating, please wait"
                },
                "ajaxNameCall": {
                    // remote json service location
                    "url": "ajaxValidateFieldName",
                    // error
                    "alertText": "* This name is already taken",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "alertTextOk": "* This name is available",
                    // speaks by itself
                    "alertTextLoad": "* Validating, please wait"
                },
                "validate2fields": {
                    "alertText": "* Please input HELLO"
                },
                //YinHaiping.SystemAettingGlobal
                "globalno1": {
                	"regex": /^(([1-9])|([1-9][0-9])|(100))$/,
                	"alertText": "* 请输入1--100之间数字"
                },
                "globalno2": {
                	"regex": /^(([1-9])|([1-9][0-9]{1,2})|([1-3][0-5][0-9]{2})|(3600))$/,
                	"alertText": "* 请输入1--3600之间数字"
                },
                "validateLength": {
                	"regex": /^.{0,50}$/,
                	"alertText":"* 请输入1~50个字符..."
                },
                "validateSpase": {
                	"regex": /^([^ ])*$/,
                	"alertText": "* 您输入的内容含有空格。。。"
                },
                "settingPort": {
                	"regex": /^(([1-9])|([1-9][0-9]{1,3})|([1-6][0-4][0-9]{3})|([1-6][0-5][0-4][0-9]{2})|([1-6][0-5][0-5][0-2][0-9])|([1-6][0-5][0-5][0-3][0-5]))$/,
                	"alertText": "* 请输入1--65535之间数字"
                },
                "settingNumber": {
                	"regex": /^[0-9]*[1-9][0-9]*$/,
                	"alertText": "* 请输入数字"
                },
                "settingMac": {
                	"regex": /^($)|(([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}$)/,
                	"alertText": "* 请输入正确的MAC地址"
                },
                "settingIp": {
                	"regex": /((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))/,
                	"alertText": "* 请输入正确的IP地址"
                },
                "settingZifang": {
                	/*"regex": /^(254|252|248|240|224|192|128|0)\.0\.0\.0|255\.(254|252|248|240|224|192|128|0)\.0\.0|255\.255\.(254|252|248|240|224|192|128|0)\.0|255\.255\.255\.(254|252|248|240|224|192|128|0)$/,
                	"alertText": "* 请输入正确的子网掩码地址"*/
                },
                "settingMAC": {
                	/*"regex": /^(254|252|248|240|224|192|128|0)\.0\.0\.0|255\.(254|252|248|240|224|192|128|0)\.0\.0|255\.255\.(254|252|248|240|224|192|128|0)\.0|255\.255\.255\.(254|252|248|240|224|192|128|0)$/,
                	"alertText": "* 请输入正确的子网掩码地址"*/
                },
                "passwordLength": {
                	"regex": /^.{6,30}$/,
                	"alertText":"* 密码必须输入6~30个字符..."
                },
                "linuxPath": {
                	"regex": /^([\/][\w-]+)*$/,
                	"alertText":"* 路径不正确..."
                }
            };
            
        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);


    
