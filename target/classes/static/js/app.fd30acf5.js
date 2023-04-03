(function () {
    "use strict";
    var e = {
        7769: function (e, t, n) {
            var s = n(9242), r = n(3396);
            const o = {id: "app"}, u = {class: "chat-window"}, a = ["disabled"], i = ["disabled"];

            function c(e, t, n, c, l, p) {
                const f = (0, r.up)("ChatBubble");
                return (0, r.wg)(), (0, r.iD)("div", o, [(0, r._)("div", u, [((0, r.wg)(!0), (0, r.iD)(r.HY, null, (0, r.Ko)(l.messages, ((e, t) => ((0, r.wg)(), (0, r.j4)(f, {
                    key: t,
                    message: e,
                    fromUser: e.fromUser
                }, null, 8, ["message", "fromUser"])))), 128))]), (0, r._)("form", {
                    onSubmit: t[1] || (t[1] = (...e) => p.sendMessage && p.sendMessage(...e)),
                    class: "input-container"
                }, [(0, r.wy)((0, r._)("input", {
                    type: "text",
                    "onUpdate:modelValue": t[0] || (t[0] = e => l.userInput = e),
                    disabled: l.sendDisable
                }, null, 8, a), [[s.nr, l.userInput]]), (0, r._)("button", {
                    type: "submit",
                    disabled: l.sendDisable
                }, "发送", 8, i)], 32)])
            }

            n(7658);
            var l = n(7139);

            function p(e, t, n, s, o, u) {
                return (0, r.wg)(), (0, r.iD)("div", {class: (0, l.C_)(u.bubbleClass)}, [(0, r._)("p", null, (0, l.zw)(n.message.text), 1)], 2)
            }

            var f = {
                props: {message: {type: Object, required: !0}, fromUser: {type: Boolean, default: !1}},
                computed: {
                    bubbleClass() {
                        return this.fromUser ? "user-bubble" : "bot-bubble"
                    }
                }
            }, d = n(89);
            const h = (0, d.Z)(f, [["render", p], ["__scopeId", "data-v-013dda6f"]]);
            var b = h, m = n(4161), v = {
                components: {ChatBubble: b}, data() {
                    return {
                        messages: [],
                        userInput: "",
                        prompt: [{role: "system", content: "你是一个有用的助手"}],
                        sendDisable: !1
                    }
                }, methods: {
                    async sendMessage(e) {
                        if (e.preventDefault(), !this.userInput) return;
                        const t = {text: this.userInput, fromUser: !0};
                        this.messages.push(t), this.userInput = "", this.sendDisable = !0, setTimeout((() => {
                            this.sendDisable = !1
                        }), 3e3);
                        const n = await this.fetchResponse(t.text), s = {text: n, fromUser: !1};
                        this.messages.push(s)
                    }, async fetchResponse(e) {
                        this.prompt.push({role: "user", content: e});
                        const t = await m.Z.post("http://172.26.10.225/chat", this.prompt);
                        console.log(t), console.log(t["data"]["choices"][0]);
                        let n = t["data"]["choices"][0]["message"]["content"],
                            s = t["data"]["choices"][0]["message"]["role"];
                        return this.prompt.push({role: s, content: n}), n = n.replace("\n", ""), n
                    }
                }
            };
            const g = (0, d.Z)(v, [["render", c]]);
            var w = g;
            (0, s.ri)(w).mount("#app")
        }
    }, t = {};

    function n(s) {
        var r = t[s];
        if (void 0 !== r) return r.exports;
        var o = t[s] = {exports: {}};
        return e[s](o, o.exports, n), o.exports
    }

    n.m = e, function () {
        var e = [];
        n.O = function (t, s, r, o) {
            if (!s) {
                var u = 1 / 0;
                for (l = 0; l < e.length; l++) {
                    s = e[l][0], r = e[l][1], o = e[l][2];
                    for (var a = !0, i = 0; i < s.length; i++) (!1 & o || u >= o) && Object.keys(n.O).every((function (e) {
                        return n.O[e](s[i])
                    })) ? s.splice(i--, 1) : (a = !1, o < u && (u = o));
                    if (a) {
                        e.splice(l--, 1);
                        var c = r();
                        void 0 !== c && (t = c)
                    }
                }
                return t
            }
            o = o || 0;
            for (var l = e.length; l > 0 && e[l - 1][2] > o; l--) e[l] = e[l - 1];
            e[l] = [s, r, o]
        }
    }(), function () {
        n.n = function (e) {
            var t = e && e.__esModule ? function () {
                return e["default"]
            } : function () {
                return e
            };
            return n.d(t, {a: t}), t
        }
    }(), function () {
        n.d = function (e, t) {
            for (var s in t) n.o(t, s) && !n.o(e, s) && Object.defineProperty(e, s, {enumerable: !0, get: t[s]})
        }
    }(), function () {
        n.g = function () {
            if ("object" === typeof globalThis) return globalThis;
            try {
                return this || new Function("return this")()
            } catch (e) {
                if ("object" === typeof window) return window
            }
        }()
    }(), function () {
        n.o = function (e, t) {
            return Object.prototype.hasOwnProperty.call(e, t)
        }
    }(), function () {
        var e = {143: 0};
        n.O.j = function (t) {
            return 0 === e[t]
        };
        var t = function (t, s) {
            var r, o, u = s[0], a = s[1], i = s[2], c = 0;
            if (u.some((function (t) {
                return 0 !== e[t]
            }))) {
                for (r in a) n.o(a, r) && (n.m[r] = a[r]);
                if (i) var l = i(n)
            }
            for (t && t(s); c < u.length; c++) o = u[c], n.o(e, o) && e[o] && e[o][0](), e[o] = 0;
            return n.O(l)
        }, s = self["webpackChunkchat_app"] = self["webpackChunkchat_app"] || [];
        s.forEach(t.bind(null, 0)), s.push = t.bind(null, s.push.bind(s))
    }();
    var s = n.O(void 0, [998], (function () {
        return n(7769)
    }));
    s = n.O(s)
})();
//# sourceMappingURL=app.fd30acf5.js.map