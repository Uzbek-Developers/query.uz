/**
 * Created by sherali on 12/8/15.
 */
var App = {
    init: function () {
        setTimeout(function () {
            App.editorConfig();
            if (prettyPrint) {
                $("code").parent("pre").addClass("prettyprint");
                prettyPrint();
            }
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

