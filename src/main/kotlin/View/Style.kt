package View

class Style {
    companion object {
        val webViewStyle = """
            /*! Color themes for Google Code Prettify | MIT License | github.com/jmblog/color-themes-for-google-code-prettify */
            .prettyprint {
                background: #000;
                font-family: Menlo, "Bitstream Vera Sans Mono", "DejaVu Sans Mono", Monaco, Consolas, monospace;
                border: 0 !important;
            }

            .pln {
                color: #fff;
            }

            /* Specify class=linenums on a pre to get line numbering */
            ol.linenums {
                margin-top: 0;
                margin-bottom: 0;
                color: #666666;
            }

            li.L0,
            li.L1,
            li.L2,
            li.L3,
            li.L4,
            li.L5,
            li.L6,
            li.L7,
            li.L8,
            li.L9 {
                padding-left: 1em;
                background-color: #000;
                list-style-type: decimal;
            }

            @media screen {

                /* string content */

                .str {
                    color: #6f0;
                }

                /* keyword */

                .kwd {
                    color: #f60;
                }

                /* comment */

                .com {
                    color: #93c;
                }

                /* type name */

                .typ {
                    color: #458;
                }

                /* literal value */

                .lit {
                    color: #458;
                }

                /* punctuation */

                .pun {
                    color: #fff;
                }

                /* lisp open bracket */

                .opn {
                    color: #fff;
                }

                /* lisp close bracket */

                .clo {
                    color: #fff;
                }

                /* markup tag name */

                .tag {
                    color: #fff;
                }

                /* markup attribute name */

                .atn {
                    color: #9c9;
                }

                /* markup attribute value */

                .atv {
                    color: #6f0;
                }

                /* declaration */

                .dec {
                    color: #fff;
                }

                /* variable name */

                .var {
                    color: #fff;
                }

                /* function name */

                .fun {
                    color: #fc0;
                }
            }
        """.trimIndent()
    }
}