package lab.util.animation;

public abstract class Animation<E> {
	
	private boolean cancelled = false;
	protected final E[] targets;
	protected int currentTargetIndex = 0;
	
	public Animation(E[] targets) {
		this.targets = targets;
	}

	public E[] getTargets() {
		return targets;
	}
	
	public int getCurrentTargetIndex() {
		return currentTargetIndex;
	}
	
	public E getCurrentTarget() {
		return targets[currentTargetIndex];
	}
	
	public void setCurrentTarget(int currentTarget) {
		this.currentTargetIndex = currentTarget;
	}

	public boolean isCancelled() {
		return cancelled;
	}
	
	public void cancel() {
		cancelled = true;
	}
	
	public void run() {
		if (equals(getValue(), targets[currentTargetIndex])) {
			if (currentTargetIndex == targets.length - 1) {
				cancel();
				return;
			}
			
			currentTargetIndex++;
			
		}
		
		changeValue();
	}
	
	public boolean equals(E current, E target) {
		return current.equals(target);
	}
	
	public abstract E getValue();
	public abstract void setValue(E e);
	public abstract void changeValue();
	
}
