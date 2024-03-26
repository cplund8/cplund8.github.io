package lab.component.fx;

import java.awt.Color;
import java.awt.Graphics;

import lab.util.Vector2;
import lab.util.animation.Animator;
import lab.util.animation.ColorLinearAnimation;

public class Particle {
	
	private final ParticleSystem system;
	private final Animator animator = new Animator();
	
	private boolean active = false;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private double width;
	private double widthChange;
	private double height;
	private double heightChange;
	private double speedChange;
	private int lifetime;
	private int life;
	private Color color;
	private ParticleShape shape;
	private Color[] colors;
	
	public Particle(ParticleSystem system) {
		this.system = system;
	}
	
	void start() {
		position = system.getSpawnArea().getRandomVector2();
		velocity = system.getVelocity().getRandomVector2();
		acceleration = system.getAcceleration().getRandomVector2();
		width = system.getParticleWidth().getRandomDouble();
		widthChange = system.getParticleWidthChange().getRandomDouble();
		height = system.getParticleHeight().getRandomDouble();
		heightChange = system.getParticleHeightChange().getRandomDouble();
		speedChange = system.getSpeedChange().getRandomDouble();
		lifetime = (int) system.getLifetime().getRandomDouble();
		color = system.getColor().getRandomColor();
		shape = system.getShape();
		colors = system.getColors();
		
		float colorFade = (float) system.getColorFade().getRandomDouble();

		life = 0;
		
		active = true;
	
		animator.addAnimation("color", new ColorLinearAnimation(colors, colorFade) {
			
			@Override
			public void setValue(Color c) {
				color = c;
			}
			
			@Override
			public Color getValue() {
				return color;
			}
			
		});
		
		
	}
	
	boolean isActive() {
		return active;
	}
	
	void stop() {
		this.active = false;
	}
	
	private void checkForCollisions() {
		checkForCollisions(position.clone(), position.add(velocity));
	}
	
	private void checkForCollisions(Vector2 oldPosition, Vector2 newPosition) {
		
		Vector2 intersect;
		
		for (Vector2[] edge : system.getCollidableEdges()) {
			
			intersect = getLineIntersectionPoint(oldPosition, newPosition, edge[0], edge[1]);
			
			if (intersect != null) {
				
				if (edge[0].getX() == edge[1].getX()) {
					edge[0] = edge[0].add(0.0001, 0);
				}
				
				if (edge[0].getY() == edge[1].getY()) {
					edge[0] = edge[0].add(0, 0.0001);
				}
				
				double m = (edge[1].getY() - edge[0].getY()) / (edge[1].getX() - edge[0].getX());
				m = 1.0 / -m;
				
				Vector2 n = new Vector2(1, m);
				
				Vector2 u = n.multiply(velocity.dot(n) / n.dot(n));
				Vector2 w = velocity.subtract(u);
				
				velocity = w.multiply(system.getFriction()).subtract(u.multiply(system.getFriction()));
				
				newPosition = intersect.add(velocity);
				
				checkForCollisions(oldPosition, newPosition);
				return;
			}
		}
		
		position = oldPosition;
		
		
		/*
		Vector2 p1, p2;
		Vector2[] collisionEdge = null;
		
		for (Vector2[] edge : system.getCollidableEdges()) {
			
			if (getLineIntersectionPoint(oldPosition, newPosition, edge[0], edge[1]) != null) {
				
				collisionEdge = edge;
				
				break;
			}
		}
		
		if (collisionEdge == null) {
			return;
		}
		
		p1 = collisionEdge[0];
		p2 = collisionEdge[1];
		
		if (p1.getX() == p2.getX()) {
			p1 = p1.add(0.0001, 0);
		}
		
		if (p1.getY() == p2.getY()) {
			p1 = p1.add(0, 0.0001);
		}
		
		double m = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
		m = 1.0 / -m;
		
		Vector2 n = new Vector2(1, m);
		
		Vector2 u = n.multiply(velocity.dot(n) / n.dot(n));
		Vector2 w = velocity.subtract(u);
		
		velocity = w.multiply(system.getFriction()).subtract(u.multiply(system.getFriction()));
		*/
		
	}
	
	
	void update() {
		if (!active) {
			return;
		}
		
		life++;
		
		if (life >= lifetime) {
			active = false;
			return;
		}
		
		if (system.getCollidableEdges().size() > 0) {
			checkForCollisions();
		}
		
		position = position.add(velocity);
		velocity = velocity.add(acceleration);
		
		if (speedChange != 0) {
			velocity = velocity.extend(speedChange);
		}
		
		width += widthChange;
		height += heightChange;
		
		animator.animate();
		
	}
	
	void draw(Graphics g, int x, int y, int w, int h) {
		if (!active) {
			return;
		}
		
		double dx = position.getX() / system.getWidth() * w + x;
		double dy = position.getY() / system.getHeight() * h + y;
		double dw = width / system.getWidth() * w;
		double dh = height / system.getHeight() * h;
		
		
		g.setColor(color);
		
		if (shape == ParticleShape.ELLIPSE) {
			g.fillOval((int) dx - (int) (dw / 2), (int) dy - (int) (dh / 2), (int) dw, (int) dh);
		} else if (shape == ParticleShape.RECTANGLE) {
			g.fillRect((int) dx - (int) (dw / 2), (int) dy - (int) (dh / 2), (int) dw, (int) dh);
		}
		
	}
	
	private static Vector2 getLineIntersectionPoint(Vector2 v1, Vector2 v2, Vector2 v3, Vector2 v4) {

		double x1, y1, x2, y2, x3, y3, x4, y4;

		x1 = v1.getX();
		y1 = v1.getY();
		x2 = v2.getX();
		y2 = v2.getY();
		x3 = v3.getX();
		y3 = v3.getY();
		x4 = v4.getX();
		y4 = v4.getY();

		double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
		
		if (denom == 0.0) { // Lines are parallel.
			return null;
		}
		
		double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
		double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
		
		if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
			// Get the intersection point.
			return new Vector2(x1 + ua * (x2 - x1), y1 + ua * (y2 - y1));
		}

		return null;
	}

}
