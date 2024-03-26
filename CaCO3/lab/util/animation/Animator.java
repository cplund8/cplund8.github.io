package lab.util.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animator {
	
	private final Map<String,Animation<?>> animationMap = new HashMap<String,Animation<?>>();
	
	public void addAnimation(String id, Animation<?> animation) {
		animationMap.put(id, animation);
	}
	
	public Animation<?> getAnimation(String id) {
		return animationMap.get(id);
	}
	
	public boolean animationExists(String id) {
		return animationMap.containsKey(id);
	}
	
	public boolean cancelAnimation(String id) {
		Animation<?> anim = getAnimation(id);
		
		if (anim != null) {
			anim.cancel();
			return true;
		}
		
		return false;
	}
	
	public void cancelAll() {
		for (Animation<?> anim : animationMap.values()) {
			anim.cancel();
		}
		
		animationMap.clear();
	}
	
	public void animate() {
		List<String> toRemove = new ArrayList<String>();
		
		for (Map.Entry<String,Animation<?>> e : animationMap.entrySet()) {
			
			if (e.getValue().isCancelled()) {
				toRemove.add(e.getKey());
				continue;
			}
			
			e.getValue().run();
			
		}
		
		for (String s : toRemove) {
			animationMap.remove(s);
		}
	}

}
