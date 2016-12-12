/**
 * 
 */
package com.enums;

/**
 * @author dipanjankarmakar
 * This enum differentiates the various modules. Used for saving comments
 */
public enum ModuleEnum {
 
	EVENTS(1),
	BROADCAST(2),
	DISCUSSION(3);
	private int value;

	private ModuleEnum(int value){
		this.value=value;
	}
	
	public int value() {
        return value;
    }
	public static ModuleEnum fromValue(int v) {
        for (ModuleEnum moduleEnum: ModuleEnum.values()) {
            if (moduleEnum.value == v) {
                return moduleEnum;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }
}
