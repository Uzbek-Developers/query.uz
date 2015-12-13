/**
 * Created by sherali on 12/8/15.
 */
var App = {
    init: function () {
        setTimeout(function () {
            App.editorConfig();

            if (prettyPrint)
                prettyPrint();
        })
    },
    editorConfig: function () {
        (function () {

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
        })();
    }
}
App.init();

