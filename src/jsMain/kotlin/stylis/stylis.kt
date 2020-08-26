@file:JsModule("stylis")
@file:JsNonModule

package stylis

external fun compile(css: String): Array<dynamic>

external fun serialize(children: dynamic, callback: dynamic): String

external fun stringify(element: dynamic, index: dynamic, children: dynamic, callback: dynamic): dynamic

external fun middleware(collection: dynamic): dynamic
