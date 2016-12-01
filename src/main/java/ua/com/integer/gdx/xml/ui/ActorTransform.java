package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ua.com.integer.gdx.xml.ui.eval.Eval;

public class ActorTransform {
	private transient Actor target;
	private String x = "0", y = "0", width = "100", height = "50";
	private String scaleX = "1", scaleY = "1";
	private String rotation = "0";
	private String originX = "0", originY = "0";
	
	public static ActorTransform start(Actor target) {
		return new ActorTransform().target(target);
	}
	
	public ActorTransform copy() {
		ActorTransform result = new ActorTransform();
		result.x = x;
		result.y = y;
		result.width = width;
		result.height = height;
		result.scaleX = scaleX;
		result.scaleY = scaleY;
		result.rotation = rotation;
		result.originX = originX;
		result.originY = originY;
		return result;
	}
	
	public ActorTransform target(Actor target) {
		this.target = target;
		return this;
	}
	
	public ActorTransform origin(float originX, float originY) {
		return originX(originX).originY(originY);
	}
	
	public ActorTransform origin(String originX, String originY) {
		return originX(originX).originY(originY);
	}
	
	public ActorTransform originX(float originX) {
		return originX(Float.toString(originX));
	}
	
	public ActorTransform originX(String originX) {
		this.originX = originX;
		return this;
	}
	
	public ActorTransform originY(float originY) {
		return originY(Float.toString(originY));
	}
	
	public ActorTransform originY(String originY) {
		this.originY = originY;
		return this;
	}
	
	public ActorTransform rotation(float rotation) {
		return rotation(Float.toString(rotation));
	}
	
	public ActorTransform rotation(String rotation) {
		this.rotation = rotation;
		return this;
	}
	
	public ActorTransform scale(float scale) {
		return scaleX(scale).scaleY(scale);
	}
	
	public ActorTransform scale(String scale) {
		return scaleX(scale).scaleY(scale);
	}
	
	public ActorTransform scaleX(float scaleX) {
		return scaleX(Float.toString(scaleX));
	}
	
	public ActorTransform scaleX(String scaleX) {
		this.scaleX = scaleX;
		return this;
	}
	
	public ActorTransform scaleY(float scaleY) {
		return scaleY(Float.toString(scaleY));
	}
	
	public ActorTransform scaleY(String scaleY) {
		this.scaleY = scaleY;
		return this;
	}
	
	public ActorTransform center() {
		return position("(PAR_W - W)/2", "(PAR_H - H)/2");
	}
	
	public ActorTransform left() {
		return x("0");
	}
	
	public ActorTransform left(float pad) {
		return left(Float.toString(pad));
	}
	
	public ActorTransform left(String pad) {
		return x("0 + " + pad);
	}
	
	public ActorTransform right() {
		return x("PAR_W - W");
	}
	
	public ActorTransform right(float pad) {
		return right(Float.toString(pad));
	}
	
	public ActorTransform right(String pad) {
		return x("PAR_W - W - " + pad);
	}
	
	public ActorTransform bottom(float pad) {
		return bottom(Float.toString(pad));
	}
	
	public ActorTransform bottom(String pad) {
		return y(pad);
	}
	
	public ActorTransform bottom() {
		return y("0");
	}
	
	public ActorTransform top(float pad) {
		return top(Float.toString(pad));
	}
	
	public ActorTransform top(String pad) {
		return y("PAR_H - H - " + pad);
	}
	
	public ActorTransform top() {
		return y("PAR_H - H");
	}
	
	public void apply() {
		applySize();
		applyPosition();
		applyOrigin();
		applyScale();
		applyRotation();
	}
	
	public void applySize() {
		target.setSize(eval(width), eval(height));
	}
	
	public void applyPosition() {
		target.setPosition(eval(x), eval(y));
	}
	
	public void applyOrigin() {
		target.setOrigin(eval(originX), eval(originY));
	}
	
	public void applyScale() {
		target.setScale(eval(scaleX), eval(scaleY));
	}
	
	public void applyRotation() {
		target.setRotation(eval(rotation));
	}
	
	public ActorTransform position(float x, float y) {
		return x(Float.toString(x)).y(Float.toString(y));
	}
	
	public ActorTransform position(String x, String y) {
		return x(x).y(y);
	}
	
	public ActorTransform size(float width, float height) {
		return width(Float.toString(width)).height(Float.toString(height));
	}
	
	public ActorTransform fillParentWidth() {
		return width("PAR_W");
	}
	
	public ActorTransform fillParentHeight() {
		return height("PAR_H");
	}
	
	public ActorTransform fillParent() {
		return size("PAR_W", "PAR_H").position(0, 0);
	}
	
	public ActorTransform fillParent(String pad) {
		return size("PAR_W - 2 * " + pad , "PAR_H - 2 * " + pad).position(pad, pad);
	}
	
	public ActorTransform size(String width, String height) {
		return width(width).height(height);
	}
	
	public ActorTransform x(float x) {
		return x(Float.toString(x));
	}
	
	public ActorTransform x(String x) {
		this.x = x;
		return this;
	}
	
	public String getX() {
		return x;
	}
	
	public ActorTransform y(float y) {
		return y(Float.toString(y));
	}
	
	public ActorTransform y(String y) {
		this.y = y;
		return this;
	}
	
	public String getY() {
		return y;
	}
	
	public ActorTransform width(float width) {
		return width(Float.toString(width));
	}
	
	public ActorTransform width(String width) {
		this.width = width;
		return this;
	}
	
	public String getWidth() {
		return width;
	}
	
	public ActorTransform height(float height) {
		return height(Float.toString(height));
	}
	
	public ActorTransform height(String height) {
		this.height = height;
		return this;
	}
	
	public String getHeight() {
		return height;
	}
	
	public String getScaleX() {
		return scaleX;
	}
	
	public String getScaleY() {
		return scaleY;
	}
	
	public String getOriginX() {
		return originX;
	}
	
	public String getOriginY() {
		return originY;
	}
	
	public String getRotation() {
		return rotation;
	}
	
	public float eval(String expression) {
		return Eval.eval(target, expression);
	}
}
