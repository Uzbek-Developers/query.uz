// Usage:
//
// var myConverter = new Markdown.Editor(myConverter, null, { strings: Markdown.local.uz });

(function () {
    Markdown.local = Markdown.local || {};
    Markdown.local.uz = {
        bold: "Qalin <strong> Ctrl+B",
        boldexample: "qalin matn",

        italic: "Qiya <em> Ctrl+I",
        italicexample: "qiya matn",

        link: "Havola <a> Ctrl+L",
        linkdescription: "havola matnini kiriting",
        linkdialog: "<p><b>Havolani qo'ying </b></p><p>http://query.uz/ \"Ixtiyoriy mayn\"</p>",

        quote: "Iqtibos <blockquote> Ctrl+Q",
        quoteexample: "Iqtibos",

        code: "Kodi namuna <pre><code> Ctrl+K",
        codeexample: "kodni qo'ying",

        image: "Rasm <img> Ctrl+G",
        imagedescription: "Rasmga izoh",
        imagedialog: "<p><b>Rasm qo'yish </b></p><p>http://query.uz/images/diagram.jpg \"Ixtiyoriy matn\"<br><br>Need <a href='http://www.google.com/search?q=free+image+hosting' target='_blank'>free image hosting?</a></p>",

        olist: "Raqamlashgan ro'yxat <ol> Ctrl+O",
        ulist: "Oddiy ro'yxat <ul> Ctrl+U",
        litem: "Ro'yxat ichi",

        heading: "Sarlavhalash <h1>/<h2> Ctrl+H",
        headingexample: "Sarlavha",

        hr: "Gorizontal chiziq <hr> Ctrl+R",

        undo: "Orqaga - Ctrl+Z",
        redo: "Oldinga - Ctrl+Y",
        redomac: "Orqaga - Ctrl+Shift+Z",

        help: "Markdown Tahrirlovchi haqida"
    };
})();