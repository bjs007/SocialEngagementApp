/**
 * 
 */
package com.enums;

/**
 * @author dipanjankarmakar
 *
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
}
