@file:JsModule("stylis")
@file:JsNonModule
package stylis

external fun compile(css: String): dynamic

external fun serialize(children: dynamic, callback: dynamic):  List<String>

external fun stringify(element: dynamic, index: dynamic, children: dynamic, callback: dynamic): dynamic

//export function serialize (children, callback) {
//export function stringify (element, index, children, callback)