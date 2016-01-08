/**
 * Created by sherali on 12/8/15.
 */
var App = {
    init: function () {
        setTimeout(function () {
            App.editorConfig();
            App.validateAddForm();
            App.handlers();
            if (prettyPrint) {
                $("code").parent("pre").addClass("prettyprint");
                prettyPrint();
            }
        });
    },
    handlers: function () {
        $("body").on("click", ".has-dropdown > a", function () {
            $(this).parent()
                .siblings().removeClass("open")
                .end().toggleClass("open");

            return false;
        });
        $("body").on("click", ".close", function () {
            $(this).closest(".open").removeClass("open");

            return false;
        });
        $("body").on("click", ".vote-btn", function () {
            var parent = $(this).closest(".box-feature");
            var postId = $(this).closest(".box-feature").find("input[name=postId]").val();
            var postType = $(this).closest(".box-feature").find("input[name=postType]").val();
            var rankType = $(this).attr("class").indexOf("down") !== -1 ? "down" : "up";
            $.getJSON('/setVote', {id: postId, postType: postType, rank: rankType}, function (data) {
                console.log(data);
                parent.find(".marked-up").removeClass("marked-up");
                parent.find(".marked-down").removeClass("marked-down");
                parent.find(".vote").addClass(data.rank > 0 ? 'marked-up' : data.rank < 0 ? 'marked-down' : '');
                parent.find(".count").text(data.voteCount);
            });
        });
    },
    tagSelectorConfig: function () {
        var TagSelector = $("#tagSelector").tagSelector({
            search: function (searchWord) {
                console.log("API ga junatish uchun kalit so`z ==> ", searchWord);

                var jsonArray = [
                    {
                        name: "Java" + parseInt(Math.random() * 100),
                        stat: parseInt(Math.random() * 100),
                        shortDesc: "Ok now i know that im not supposed to ask this kind of question but i'm at" +
                            "the end of my game...Ok now i know that im not supposed to ask this kind of question " +
                            "but i'm at the end of my game... " +
                            "I just cannot find the name of this game.",
                        moreLink: "www.so.com"
                    },
                    {
                        name: "Java" + parseInt(Math.random() * 100),
                        stat: parseInt(Math.random() * 100),
                        shortDesc: "Ok now i know that im not supposed to ask this kind of question but i'm at" +
                            "the end of my game...Ok now i know that im not supposed to ask this kind of question " +
                            "but i'm at the end of my game... " +
                            "I just cannot find the name of this game.",
                        moreLink: "www.so.com"
                    },
                    {
                        name: "Java" + parseInt(Math.random() * 100),
                        stat: parseInt(Math.random() * 100),
                        shortDesc: "Ok now i know that im not supposed to ask this kind of question but i'm at" +
                            "the end of my game...Ok now i know that im not supposed to ask this kind of question ",
                        moreLink: "www.so.com"
                    }
                ];

                //API dan kelgan datani(array bulishi kerak, va yuqoridagi formatga utkazish kerak)
                TagSelector.setOptionContent(jsonArray);
            }
        });
    },
    validateAddForm: function () {

    },
    editorConfig: function () {
        (function () {
            if ($("[class*=wmd-panel]").length) {
                var converter1 = Markdown.getSanitizingConverter();

                converter1.hooks.chain("preBlockGamut", function (text, rbg) {
                    return text.replace(/^ {0,3}""" *\n((?:.*?\n)+?) {0,3}""" *$/gm, function (whole, inner) {
                        return "<blockquote>" + rbg(inner) + "</blockquote>\n";
                    });
                });

                var help = function () {
                    alert("Sizga yordam kerakmi?");
                }
                var options = {
                    helpButton: { handler: help },
                    strings: Markdown.local.uz
                };
                var editor1 = new Markdown.Editor(converter1, "", options);
                editor1.run();
//                var editorIdPostfixList = [];
//                for (i = 0; i < 20; i++) {
//                    editorIdPostfixList.push("-comment-" + i);
//                }
//                for (i = 0; i < editorIdPostfixList.length; i++) {
//                    var editor1 = new Markdown.Editor(converter1, editorIdPostfixList[i], options);
//                    editor1.run();
//                }

            }
        })();
    }
}
App.init();

