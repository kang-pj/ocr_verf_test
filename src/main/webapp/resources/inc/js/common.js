/**
 *
 */
var dataJSON;
(function ($) {
    var $this;
    $.ajaxSetup({
        cache: false,
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        url: "",
        error: function (jqXHR, textStatus) {
            if (jqXHR.status != 0) {
                var win;
                if (401 == jqXHR.status) {
                    win = window.open(getContextPath() + "/login", "errorPop", "width=304, height=198, resizable=no, left=300,top=300");
                } else if (403 == jqXHR.status) {
                    console.log('권한 없음 : ' + this.url);
                    var token = $("meta[name='_csrf']").attr("content");

                    $('<form action="' + getContextPath() + '/error/forbiddenP" method="post"></form>')
                        .append($("<input type='hidden' name='responseText'>").val(jqXHR.responseText))
                        .append($("<input type='hidden' name='_csrf'>").val(token))
                        .appendTo('body').submit();

                } else {
                    win = window.open(getContextPath() + "/error/errorPop", "errorPop", "width=304, height=198, resizable=no, left=300,top=300");
                }
                win.focus();
            }
        }
    });

    $(document).ready(function () {

        $("form").submit(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
        });

        $('#common_loading')
            .ajaxStart(function () {
                $(this).show();
            })
            .ajaxStop(function () {
                //$(this).hide();
                $(this).hide();
            });
        $.ajaxSetup({
            cache: true,
            dataType: "json",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            url: ""
        });

        //전화걸기
        $(".call").click(function () {
            var telNo = $(this).attr("tel"); // 클릭한 전화번호
            var ctrlYr = $(this).attr("ctrlYr");
            var instCd = $(this).attr("instCd");
            var prdtCd = $(this).attr("prdtCd");
            var ctrlNo = $(this).attr("ctrlNo");
            var calMntClsCd = $(this).attr("calMntClsCd");
            if (("undefined" == typeof (calMntClsCd))) {
                calMntClsCd = "01";
            }

            if (telNo == "" || telNo == null) {
                telNo = $(this).text();
            }

            // call click
            telNo = telNo.split("-").join("");
            telNo = telNo.split(" ").join("");
            telNo = telNo.split("(").join("");
            telNo = telNo.split(")").join("");

            //관리번호 조합
            ctrlNo = ctrlYr + instCd + prdtCd + ctrlNo;

            $.ajax({
                url: "/rf-ocr-verf/getUserInfo",  //세션정보 가지고오기
                type: "PUT",
                success: function (result) {
                    var inPhNo = result.userInfo.inPhNo; //사용자 내선번호
                    var usrId = result.userInfo.usrId; //사용자 아이디

                    if (result != null && !result.empty) {
                        if (telNo == "" || telNo == null) {
                            return;
                        }

                        var sendUrl = "";

                        //녹취변경중
                        sendUrl = "/rf-ocr-verf/_dispCall?Customer_Num=" + telNo + "&Ext=" + inPhNo + "&ctrlNo=" + ctrlNo + "&userId=" + usrId + "&kind=CALLJL&calMntClsCd=" + calMntClsCd;
                        window.open(sendUrl, "clickToCall", "width=1200, height=700");
                    }
                }
            });
        });

    }); // .ready


    //글자  입력 제한
    $.fn.extend({
        limiter: function (limit, elem) {
            //$(this).on("focus blur keypress keyup", function() {
            $(this).on("keypress keyup", function () {
                setCount(this, elem);
            });

            function setCount(src, elem) {
                if (typeof (src) == 'undefined') {
                    return;
                }
                var charsTotal = src.value.length;
                var charsCount = 1;
                var chars = 0;

                for (var i = 0; i < charsTotal; i++) {

                    var charCode = src.value.charCodeAt(i);

                    charsCount = 2;

                    chars += charsCount;

                    if ((chars > limit) && (2 == charsCount)) {
                        //alert("chars:"+chars+", limit:"+limit+", charsCount:"+charsCount);
                        src.value = src.value.substr(0, (chars - 2));
                        //chars = limit;
                        //chars = chars - 2;
                        //alert("최대 "+limit+"byte까지 입력가능합니다.");
                        //break;
                    } else if ((chars > limit) && (1 == charsCount)) {
                        //alert("chars:"+chars+", limit:"+limit+", charsCount:"+charsCount);
                        src.value = src.value.substr(0, (chars - 1));
                        //chars = limit;
                        //chars = chars - 1;
                        //alert("최대 "+limit+"byte까지 입력가능합니다.");
                        //break;
                    }
                }
                elem.html(chars);
            }

            setCount($(this)[0], elem);
        }
    });

    //글자  입력 제한(1byte로 체크)
    $.fn.extend({
        limiter2: function (limit, elem) {
            //$(this).on("focus blur keypress keyup", function() {
            $(this).on("keypress keyup", function () {
                setCount(this, elem);
            });

            function setCount(src, elem) {
                var charsTotal = src.value.length;
                var charsCount = 1;
                var chars = 0;

                for (var i = 0; i < charsTotal; i++) {

                    var charCode = src.value.charCodeAt(i);
                    if (charCode == 10) { //엔터
                        charsCount = 2;
                    } else if (charCode > 128) {
                        charsCount = 2;
                    } else {
                        charsCount = 1;
                    }

                    chars += charsCount;

                    if ((chars > limit) && (2 == charsCount)) {
                        src.value = src.value.substr(0, (chars - 2));
                    } else if ((chars > limit) && (1 == charsCount)) {
                        src.value = src.value.substr(0, (chars - 1));
                    }
                }
                elem.html(chars);
            }

            setCount($(this)[0], elem);
        }
    });

    // amt
    $.fn.maskamt = function (options) {
        var opts = $.extend({}, $.fn.maskamt.defaults, options);
        return this.each(function () {
            $this = $(this);
            var o = $.meta ? $.extend({}, opts, $this.data()) : opts;

            if (true == $this.is("input")) { //input
                var str = $this.val();
                $this.val($this.val().toString().replace(new RegExp("(^\\d{" + ($this.val().toString().length % 3 || -1) + "})(?=\\d{3})"), "$1" + o.delimiter).replace(/(\d{3})(?=\d)/g, "$1" + o.delimiter));
            } else { //text
                var str = $this.html();
                $this.html($this.html().toString().replace(new RegExp("(^\\d{" + ($this.html().toString().length % 3 || -1) + "})(?=\\d{3})"), "$1" + o.delimiter).replace(/(\d{3})(?=\d)/g, "$1" + o.delimiter));
            }

            $(this).focusin(function () {
                $(this).val($(this).val().replace(/\,/g, ''));
            });
        });
    };

    $.fn.maskamtOfInput = function (options) {
        var opts = $.extend({}, $.fn.maskamtOfInput.defaults, options);
        return this.each(function () {
            $this = $(this);
            var o = $.meta ? $.extend({}, opts, $this.data()) : opts;

            $(this).focusout(function () {
                $this = $(this);
                $this.val($this.val().toString().replace(new RegExp("(^\\d{" + ($this.val().toString().length % 3 || -1) + "})(?=\\d{3})"), "$1" + o.delimiter).replace(/(\d{3})(?=\d)/g, "$1" + o.delimiter));
            });

            $(this).focusin(function () {
                $(this).val($(this).val().replace(new RegExp(o.delimiter, "g"), ''));
            });
        });
    };

    $.fn.maskamt.defaults = {
        delimiter: ','
    };

    $.fn.maskamtOfInput.defaults = {
        delimiter: ','
    };

    // tel
    $.fn.masktel = function (options) {	//text input인지 구분하는 if문 제거(text조건만, input은 아래 masktelOfInput에서 사용)
        return this.each(function () {
            $this = $(this);
            var str = $this.text();
            str = str.split(" ").join(""); //공백제거
            str = ((str.replace(/\(/g, '')).replace(/\)/g, '')).replace(/\-/g, ''); //괄호 및 '-' 제거

            if (str.indexOf("****") < 0) {
                str = str.replace(/(^01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/, "$1-$2-$3");
                $(this).poshytip({
                    //content: str+" <a onclick='poshytipHide();' style='cursor:pointer;'>[닫기]</a>",
                    content: str,
                    showOn: 'none',
                    alignTo: 'target',
                    alignX: 'inner-left',
                    offsetX: 0,
                    offsetY: 0,
                    showTimeout: 1
                });

                $(this).mouseover(function () {
                    $(this).poshytip('show');
                });

                $(this).mouseout(function () {
                    $(this).poshytip('hide');
                });

                var strArr = str.split('-');
                if (3 == strArr.length) {
                    $this.attr("tel", str);
                    str = strArr[0] + "-" + strArr[1] + "-" + "****";
                } else {
                    $this.attr("tel", str);
                    if (strArr.toString().length < 5) {
                        str = strArr.toString();
                    } else {
                        str = strArr.toString();
                        var strA = str.substr(0, str.length - 4);
                        str = strA + "****";
                    }
                }
                $this.html(str);
            }
        });
    };


    // tel
    $.fn.masktelnone = function (options) {
        return this.each(function () {
            $this = $(this);
            if (true == $this.is("input")) { //input
                var str = $this.val();
                str = str.split(" ").join(""); //공백제거
                str = ((str.replace(/\(/g, '')).replace(/\)/g, '')).replace(/\-/g, ''); //괄호 및 '-' 제거
                $this.val(str.replace(/(^01[016789]{1}|02|0[5,3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/, "$1-$2-$3"));
            } else { //text
                var str = $this.html().trim();

                str = str.split(" ").join("");

                str = str.replace(/(^01[016789]{1}|02|0[5,3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/, "$1-$2-$3");

                var strArr = str.split('-');

                if (3 == strArr.length) {
                    str = strArr[0] + "-" + strArr[1] + "-" + strArr[2];
                }
                $this.html(str);
            }
        });
    };

    // telOfInput
    $.fn.masktelOfInput = function (options) {
        return this.each(function () {
            $this = $(this);
            var str = $this.val();
            str = str.toString().split(" ").join(""); //공백제거
            str = (((str.replace(/\(/g, '')).replace(/\)/g, '')).replace(/\-/g, '')).replace(/\*/g, ''); //괄호 및 '-' 제거
            $this.val(str.replace(/(^01[016789]{1}|02|0[3-6]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/, "$1-$2-$3"));

            if ($this.val().indexOf("****") < 0) {
                $(this).focusout(function () {
                    $this = $(this);
                    var str = $this.val();
                    str = str.toString().split(" ").join("");
                    var strArr = str.split('-');
                    // console.log("if타기전 : " + strArr[0] + " " + strArr[1] + " "+ strArr[2]);
                    if (3 == strArr.length) {
                        $this.attr("tel", str);
                        str = strArr[0] + "-" + strArr[1] + "-" + "****";
                    } else {
                        $this.attr("tel", str);
                        if (strArr.toString().length < 5) {
                            str = strArr.toString();
                        } else {
                            str = strArr.toString();
                            var strA = str.substr(0, str.length - 4);
                            str = strA + "****";
                        }
                    }
                    $this.val(str);
                });
                $(this).focusin(function () {
                    $(this).val($(this).val().replace(/\-/g, ''));
                });
            }
        });
    };

    // jumin no
    $.fn.maskjuminno = function (options) {
        return this.each(function () {
            $this = $(this);
            var str = $this.html();
            if (trim(nvl(str)) == "") $this.html("");
            else if (str.length == 13) $this.html(str.substring(0, 6) + "-" + str.substring(6, 13));
            else $this.html(str);
        });
    };

    // jumin no Of input
    $.fn.maskjuminnoOfInput = function (options) {
        return this.each(function () {
            $(this).focusout(function () {
                $this = $(this);
                var str = $this.val();
                if (trim(nvl(str)) == "") $this.val("");
                else if (str.length == 13) $this.val(str.substring(0, 6) + "-" + str.substring(6, 13));
                else $this.val(str);
            });

            $(this).focusin(function () {
                $(this).val($(this).val().replace(/\-/g, ''));
            });
        });
    };

    // zipcode Of td
    $.fn.maskZipcode = function (options) {
        return this.each(function () {
            $this = $(this);
            var str = $this.html();
            if (trim(nvl(str)) == "") $this.html("");
            else if (str.length == 6) $this.html(str.substring(0, 3) + "-" + str.substring(3, 6));
            else $this.html(str);
        });
    };

    // zipcode Of input
    $.fn.maskZipcodeOfInput = function (options) {
        return this.each(function () {
            $(this).focusout(function () {
                $this = $(this);
                var str = $this.val();
                if (trim(nvl(str)) == "") $this.val("");
                else if (str.length == 6) $this.val(str.substring(0, 3) + "-" + str.substring(3, 6));
                else $this.val(str);
            });

            $(this).focusin(function () {
                $(this).val($(this).val().replace(/\-/g, ''));
            });
        });
    };

    // 마이너스('-') 문자 제거
    $.fn.removalMinus = function (options) {
        return this.each(function () {
            $(this).val($(this).val().replace(/\-/g, ''));
        });
    };

    // date 형식
    $.fn.maskDate = function (options) {
        return this.each(function () {
            $this = $(this);
            var str = $this.html();
            if (trim(nvl(str)) == "") $this.html("");
            else if (str.length == 8) $this.html(str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8));
            else $this.html(str);
        });
    };

    // date 형식
    $.fn.maskDateOfInput = function (options) {
        return this.each(function () {
            $(this).focusout(function () {
                $this = $(this);
                var str = $this.val();
                if (trim(nvl(str)) == "") $this.val("");
                else if (str.length == 8) $this.val(str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8));
                else $this.val(str);
            });

            $(this).focusin(function () {
                $(this).val($(this).val().replace(/\-/g, ''));
            });
        });
    };

    // 문자중에 숫자를 * 로 변경
    $.fn.maskAddrNum = function (options) {
        return this.each(function () {
            $this = $(this);
            var str = $this.html();

            if (trim(nvl(str)) == "")
                $this.html("");
            else {
                $this.attr("title", str);
                str = str.replace(/\d/g, '*');
                $this.html(str);
            }
        });
    };

    // 이름에 두번째 문자 * 로 변경
    $.fn.maskName = function (options) {
        return this.each(function () {
            $this = $(this);
            var str = $this.html();

            if (trim(nvl(str)) == "")
                $this.html("");
            else {
                $this.attr("title", str);
                const arrStr = str.split(/\n/g);

                var rsltStr = '';
                arrStr.forEach(function (item, index, arr2) {
                    var strItm = item.replace(/\n/g, "");
                    var strLen = strItm.length;

                    if (strLen > 1) {
                        if (strLen == 2) {
                            strItm = strItm.substring(0, 1) + "*";
                        } else {
                            strItm = strItm.substring(0, 1) + "*".repeat(strLen - 2) + strItm.substring(strLen - 1, strLen);
                        }
                        rsltStr += strItm + '\n';
                    }
                });

                str = rsltStr;
                $this.html(str);
            }
        });
    };
    // Validation 체크
    $.submitValidation = function (caller) {
        var check = true;

        $(caller).find("[class^=validate]").each(function () {
            var targetId = $(this).attr("id");
            var targetName = $(this).attr("name");
            var targetTitle = $(this).attr("title");
            var targetAlt = $(this).attr("alt");

            var tarGetTag = $(this).parent().prev();	// 체크항목의 제목 값인 TH 태그 찾기

            // 필수 항목값 체크
            if ($(this).val() === "") {
                check = false;

                // TH 태그일 경우
                if (tarGetTag.prop("tagName") === "TH") {
                    alert(tarGetTag.text() + " : 항목은 필수 입력 값 입니다.");
                    $(this).focus();

                    return false;	// true 하면 class="validate" 항목들 계속 체킹함...
                }

                // TH 태그가 아닐 경우
                else {
                    alert($(this).attr("alt") + " : 항목은 필수 입력 값 입니다.");
                    $(this).focus();

                    return false;	// true 하면 class="validate" 항목들 계속 체킹함...
                }
            }
        });

        // 화면 mask 값 처리
        if (check) {
            $(".num").focusin();				// 금액 콤마',' 제거
            $(".maskjuminnoOfInput").focusin();	// 주민번호 마이너스'-' 제거
            $(".maskZipcodeOfInput").focusin();	// 우편번호 마이너스'-' 제거
            $(".maskDateOfInput").focusin();	// 날짜 마이너스'-' 제거
        }

        return check;
    };

    // Numeric only control handler
    jQuery.fn.ForceNumericOnly =
        function () {
            return this.each(function () {
                $(this).keydown(function (e) {
                    var key = e.charCode || e.keyCode || 0;
                    // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                    return (
                        key == 8 ||
                        key == 9 ||
                        key == 46 ||
                        (key >= 37 && key <= 40) ||
                        (key >= 48 && key <= 57) ||
                        (key >= 96 && key <= 105));

                });
                $(this).focusout(function () {
                    $(this).val($(this).val().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
                });
                $(this).focusin(function () {
                    $(this).val($(this).val().replace(/\,/g, ''));
                });
            });
        };


    $.datepicker.regional['kr'] = {
        closeText: '닫기',
        prevText: '<',
        nextText: '>',
        currentText: '현재',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        weekHeader: '주',
        dateFormat: 'yy-mm-dd',
        timeFormat: 'hh:mm ss',
        hourText: '시',
        minuteText: '분',
        secondText: '초',
        firstDay: 7,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: ''
    };
    $.datepicker.setDefaults($.datepicker.regional['kr']);
})(jQuery);

function formToJSON(sel) {
    var o = {};
    $(".num").focusin();
    var a = sel.serializeArray();
    $(".num").focusout();

    $.each(a, function () {
        if ((sel.find(":input[name='" + this.name + "']").is(":hidden"))
            || sel.find(':input[name=' + this.name + ']').is(":visible")
            || sel.find('select[name=' + this.name + ']').is(":hidden")
            || sel.find('select[name=' + this.name + ']').is(":visible")
            || sel.find('textarea[name=' + this.name + ']').is(":visible")
        ) {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        }
    });

    return o;
}

function setJSON2ElementIdVal(datas) {
    var name;

    for (name in datas) {
        if (typeof datas[name] === 'function')
            continue;

        $("#" + name).text(nvl(datas[name]));
    }
}

//dom id 뒤에 순번이 붙어서 있는 객체에 text value에 값을 설정한다.
function JSON2ElementIdArrVal(index, datas) {
    var name;

    for (name in datas) {
        if (typeof datas[name] === 'function')
            continue;

        $("#" + name + index).text(nvl(datas[name]));
    }
}

//dom id 뒤에 순번이 붙어서 있는 객체에 text value에 값을 설정한다.
function JSON2ElementIdArrVal2(index, datas, namePostfix) {
    var name;

    for (name in datas) {
        if (typeof datas[name] === 'function')
            continue;

        $("#" + name + namePostfix + index).text(nvl(datas[name]));
    }
}

function JSON2FormValueSetting(frmID, datas) {
    var name;

    for (name in datas) {
        if (typeof datas[name] === 'function')
            continue;

        $(frmID + " input:text[name='" + name + "']").val(nvl(datas[name]));
        $(frmID + " input:hidden[name='" + name + "']").val(nvl(datas[name]));
        $(frmID + " input:radio[name='" + name + "']").filter('input[value=' + nvl(datas[name]) + ']').attr("checked", "checked");
        $(frmID + " input:checkbox[name='" + name + "']").filter('input[value=' + nvl(datas[name]) + ']').attr("checked", "checked");
        $(frmID + " textarea[name='" + name + "']").val(nvl(datas[name]));
        $(frmID + " select[name='" + name + "']").children('option[value=' + nvl(datas[name]) + ']').attr("selected", "true");
    }
}

function JSON2FormValueSetting2(frmID, datas, namePostfix) {
    var name;
    for (name in datas) {
        if (typeof datas[name] === 'function')
            continue;

        $(frmID + " input:text[name='" + name + namePostfix + "']").val(nvl(datas[name]));
        $(frmID + " input:hidden[name='" + name + namePostfix + "']").val(nvl(datas[name]));
        $(frmID + " input:radio[name='" + name + namePostfix + "']").filter('input[value=' + nvl(datas[name]) + ']').attr("checked", "checked");
        $(frmID + " textarea[name='" + name + namePostfix + "']").val(nvl(datas[name]));
        $(frmID + " select[name='" + name + namePostfix + "']").children('option[value=' + nvl(datas[name]) + ']').attr("selected", "true");
    }
}

function JSON2FormValueSetting3(frmID, datas) {
    var name;

    for (name in datas) {
        if (typeof datas[name] === 'function')
            continue;

        $(frmID + " input:text[name='" + name + "']").val(nvl(datas[name]));
        $(frmID + " input:hidden[name='" + name + "']").val(nvl(datas[name]));
        $(frmID + " input:radio[name='" + name + "']").filter('input[value=' + nvl(datas[name]) + ']').attr("checked", "checked");
        $(frmID + " textarea[name='" + name + "']").val(nvl(datas[name]));
        $(frmID + " select[name='" + name + "']").children('option[value=' + nvl(datas[name]) + ']').attr("selected", "true");
        $(frmID + " p[name='" + name + "']").text(nvl(datas[name]));
    }
}

function nvl(value) {
    return value == "null" || value == null ? "" : value;
}

function nvl2(value, rtnVal) {
    return value == "undefined" || value == "null" || value == null ? rtnVal : value;
}

function trim(str) {
    return str.replace(/^\s*/, '').replace(/\s*$/, '');
}

function juminFormat(juminNo) {
    var str = juminNo;
    if (nvl(str) == "") return "";
    if (str.length == 13) return str.substring(0, 6) + "-" + str.substring(6, 13);
    else return str;
}

// 날짜 및 금액등 구분값 주기
function setMaskClass() {
    // 금액 마스크
    $(".amt").maskamt();
    $(".num").ForceNumericOnly();
    $(".num").focusout();

    $(".maskamtOfInput").maskamtOfInput();
    $(".maskamtOfInput").focusout();

    // 날짜픽커
    $('.yyyymmdd').datetimepicker({
        showHour: false,
        showMinute: false
    });

    // 날짜픽커
    $('.yyyymmddhh24miss').datetimepicker({
        showSecond: true,
        timeFormat: 'hh:mm:ss'
    });

    // 주민번호
    $(".maskjuminno").maskjuminno();
    $(".maskjuminnoOfInput").maskjuminnoOfInput();
    $(".maskjuminnoOfInput").focusout();

    // 날짜
    $(".maskDate").maskDate();
    $(".maskDateOfInput").maskDateOfInput();
    $(".maskDateOfInput").focusout();

    // 전화번호
    $(".masktel").masktel();
    $(".masktelnone").masktelnone();
    $(".masktelOfInput").masktelOfInput();
    $(".masktelOfInput").focusout();

    // 우편번호
    $(".maskZipcode").maskZipcode();
    $(".maskZipcodeOfInput").maskZipcodeOfInput();
    $(".maskZipcodeOfInput").focusout();
}

// 개인정보 마스킹
function setPIPMaskClass() {
    // 주소
    $(".maskAddrNum").maskAddrNum();
    // 이름
    $(".maskName").maskName();
}

//날짜 및 금액등 구분값 주기
function outMaskClass() {
    // 금액 마스크
    $(".amt").focusin();
    //숫자 마스크
    $(".num").focusin();
    // 주민번호
    $(".maskjuminnoOfInput").focusin();
    // 날짜
    $(".maskDateOfInput").focusin();
    // 전화번호
    $(".masktelOfInput").focusin();
    // 우편번호
    $(".maskZipcodeOfInput").focusin();
}


var Base64 = {

// private property
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

// public method for encoding
    encode: function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

        input = Base64._utf8_encode(input);

        while (i < input.length) {

            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
                this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

        }

        return output;
    },

// private method for UTF-8 encoding
    _utf8_encode: function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";

        for (var n = 0; n < string.length; n++) {

            var c = string.charCodeAt(n);

            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    }
};

//gubun: Y년M월D일, date: 기준일, val: 기간
function calDate(gubun, date, val, format) {
    var fromDate = new Date(date);
    var Y = fromDate.getFullYear();//년 가져오기
    var M = fromDate.getMonth();//월 가져오기
    var D = fromDate.getDate();//일가져오기

    if (gubun == 'Y') {
        Y = parseInt(eval(Y + val));
    } else if (gubun == 'M') {
        M = parseInt(eval(M + val));
    } else if (gubun == 'D') {
        D = parseInt(eval(D + val));
    } else {
        alert("구분자가 틀립니다");
        return false;
    }
    fromDate = new Date(Y, M, D);
    return $.format.date(fromDate, format);
}

//주민번호 변환
function chgRegNo(str) {
    if (str != null) {
        var chgStr = str.substring(0, 6) + "-" + str.substring(6, 13);
    }
    return chgStr;
}

//우편번호 변환
function chgPostNo(str) {
    if (str != null) {
        var chgStr = str.substring(0, 3) + "-" + str.substring(3, 6);
    }
    return chgStr;
}

//전화번호 변환
function chgTelNo(str) {
    if (str != null) {
        var chgStr = str.replace(/(^01[016789]{1}|02|0[5,3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/, "$1-$2-$3");
    }
    return chgStr;
}

//input 바이트 체크
function checkByte(objname, maxLength) {
    var tcount = 0;
    var str = objname.value;
    var length = str.length;
    var tcountPrev = 0;
    var objstr2 = ""; // 허용된 글자수까지만 포함한 최종문자열

    for (var i = 0; i < length; i++) {
        var byteStr = str.charAt(i);
        if (escape(byteStr).length > 4) {
            tcountPrev = tcount;
            tcount += 2;
        } else {
            tcountPrev = tcount;
            tcount += 1;
        }
    }

    if (tcount > maxLength) {
        alert((maxLength / 2) + "자(" + maxLength + "byte) 이내로 작성해주세요.");

        objstr2 = str.substr(0, tcountPrev);
        objname.value = objstr2;

        objname.focus();
        return false;
    } else {
        return true;
    }
}

//숫자만 입력 가능
function checkNum(obj) {
    var word = obj.value;
    var str = "1234567890";
    for (var i = 0; i < word.length; i++) {
        if (str.indexOf(word.charAt(i)) < 0) {
            alert("숫자 조합만 가능합니다.");
            obj.focus();
            return false;
        }
    }
}


//0채우기
function fillzero(obj, len) {
    obj = '000000000000000' + obj;
    return obj.substring(obj.length - len);
}

//열람일시 시분초
function chgInqDttm(dt) {
    if (dt != null) {
        dt = dt.substring(0, 4) + "-" + dt.substring(4, 6) + "-" + dt.substring(6, 8) + "  " + dt.substring(8, 10) + ":" + dt.substring(10, 12) + " " + dt.substring(12, 14);
    }
    return nvl(dt);
}

//date 변환  YYYY-MM-DD
function dateChg(date) {
    if (date != null) {
        date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
    }
    return date;
}

//특수문자 제거하기
function specialCharRemove(obj) {
    var val = obj;
    var pattern = /[^(가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9)]/gi;   // 특수문자 제거
    //var pattern = /[^(0-9)]/gi;   // 숫자이외는 제거
    if (pattern.test(val)) {
        //alert(val.replace(pattern,""));
        obj = val.replace(pattern, "");
    }
    return obj;
}

//null 또는 공백일경우  0 리턴하기
function isNullCheck(obj) {
    if (obj == null || obj == "") {
        obj = "0";
    }
    return obj;
}

//날짜 유효성 체크
function isValidDate(param) {

    try {

        param = param.replace(/-/g, '');

        // 자리수가 맞지않을때

        if (isNaN(param) || param.length != 8) {
            return false;
        }

        var year = Number(param.substring(0, 4));
        var month = Number(param.substring(4, 6));
        var day = Number(param.substring(6, 8));

        var dd = day / 0;

        if (month < 1 || month > 12) {

            return false;

        }

        var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

        var maxDay = maxDaysInMonth[month - 1];

        // 윤년 체크
        if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
            maxDay = 29;
        }

        if (day <= 0 || day > maxDay) {
            return false;
        }

        return true;

    } catch (err) {
        return false;
    }

}

//날짜시분초 체크
function dateVali(dtId, hhId, miId, msg) {
    var mvdt = $("#" + dtId).val();
    var isMvdt = false;
    if (mvdt != "") {
        if (!isValidDate(mvdt)) {
            alert(msg + "일자를 올바르게 입력하세요. ");
            return false;
        }
        isMvdt = true;
    }
    var telNo = $(this).attr("tel"); // 클릭한 전화번호
    var mvhh = $("select[id=" + hhId + "]").val();
    var mvmi = $("select[id=" + miId + "]").val();
    if (mvhh != '' || mvmi != '') {
        if (mvdt == '') {
            alert(msg + "일자를 입력하세요.");
            return false;
        }

        //둘다 입력되야 함.
        if (mvhh == '' || mvmi == '') {
            alert(msg + "일시를 입력하세요.");
            return false;
        }
    }
    //둘다 비어있는 경우
    else {
        if (isMvdt) {
            alert(msg + "일시를 입력하세요.");
            return false;
        }
    }

    return true;
}

//string을 date(날짜) 값으로 변환하기 (YYYY-MM-DD)
function stringDateChg(setDate) {
    setDate = specialCharRemove(setDate); //특수 문자제거
    var year = setDate.substr(0, 4);
    var month = setDate.substr(4, 2);
    var day = setDate.substr(6, 2);
    var date = new Date(year, month, day);
    return date;
}

function poshytipHide() {
    $('.masktel').poshytip('hide');
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    if (token && header) {
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);

        });
    }
});


function goCommonCrossLogin(site, redirecturl, newTabYn, sessionkey, refreshkey) {
    var url;
    var thisUrl = location.href;

    var crossLoginForm = document.createElement("form");
    crossLoginForm.setAttribute("name", "crossLoginForm");
    crossLoginForm.setAttribute("method", "POST");

    var sessionTxt = document.createElement("input");
    var refreshTxt = document.createElement("input");
    var inputPwTxt = document.createElement("input");
    var redirectTxt = document.createElement("input");

    sessionTxt.setAttribute("name", "sessionkey");
    sessionTxt.setAttribute("type", "hidden");
    sessionTxt.setAttribute("value", sessionkey);

    refreshTxt.setAttribute("name", "refreshkey");
    refreshTxt.setAttribute("type", "hidden");
    refreshTxt.setAttribute("value", refreshkey);

    redirectTxt.setAttribute("name", "redirecturl");
    redirectTxt.setAttribute("type", "hidden");
    redirectTxt.setAttribute("value", redirecturl);

    crossLoginForm.appendChild(sessionTxt);
    crossLoginForm.appendChild(refreshTxt);
    crossLoginForm.appendChild(redirectTxt);

    if (thisUrl.indexOf(site) > -1) {
        url = redirecturl;
    } else if (site === 'rf-ocr-verf') {
        url = redirecturl.substring(0, redirecturl.indexOf(site)) + site + "/login";

        var data = {"sessionkey": sessionkey, "refreshkey": refreshkey, "redirecturl": redirecturl};
        inputPwTxt.setAttribute("name", "inputPw");
        inputPwTxt.setAttribute("type", "hidden");
        inputPwTxt.setAttribute("value", JSON.stringify(data));

        crossLoginForm.appendChild(inputPwTxt);
    } else {
        url = redirecturl.substring(0, redirecturl.indexOf(site)) + site + "/crosslogin";
    }

    crossLoginForm.setAttribute("action", url);
    if (newTabYn === 'Y') {
        crossLoginForm.setAttribute("target", '_blank');
    }
    document.body.appendChild(crossLoginForm);

    crossLoginForm.submit();
}