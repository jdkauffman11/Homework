/**
 * Encapsulation of key attributes of a 3D object.  Each object has a position,
 * rotation, and scale.  The object's transformation matrix is defined as the product of
 * three transformations based on position * rotation * scale.  Each object
 * has a list of child objects and a hook, drawFunction, for rendering the
 * object and then recursively rendering all the child objects.
 */
var CS336Object = function(drawFunction)
{
  // children of this object
  this.children = [];
  
  // position of this object
  this.position = new Vector3();

  // current rotation matrix
  this.rotation = new Matrix4();
  
  // scale for this object
  this.scale = new Vector3([ 1, 1, 1 ]);

  // the object's current transformation, to be calculated
  // as translate * rotate * scale
  // matrix is cached on call to getMatrix
  this.matrix = null;
  this.matrixNeedsUpdate = true;

  // if caller doesn't supply a function, use an empty one
  // for dummy objects
  this.drawObject = drawFunction || function(){};
};

/**
 * Sets the position.
 * @param x
 * @param y
 * @param z
 */
CS336Object.prototype.setPosition = function(x, y, z)
{
  this.position = new Vector3([ x, y, z ]);
  this.matrixNeedsUpdate = true;
};

/**
 * Sets the scale.
 * @param x
 * @param y
 * @param z
 */
CS336Object.prototype.setScale = function(x, y, z)
{
  this.scale = new Vector3([ x, y, z ]);
  this.matrixNeedsUpdate = true;
};

/**
 * Sets the current rotation matrix.
 * @param angle
 * @param x
 * @param y
 * @param z
 */
CS336Object.prototype.setRotation = function(rotationMatrix)
{
  this.rotation = new Matrix4(rotationMatrix);
  this.matrixNeedsUpdate = true;
};

/**
 * Returns the current transformation matrix, defined as
 * translate * rotate * scale.
 * @returns
 */
CS336Object.prototype.getMatrix = function()
{
  if (this.matrixNeedsUpdate)
  {
    var px, py, pz, sx, sy, sz;
    px = this.position.elements[0];
    py = this.position.elements[1];
    pz = this.position.elements[2];
    sx = this.scale.elements[0];
    sy = this.scale.elements[1];
    sz = this.scale.elements[2];

    this.matrixNeedsUpdate = false;
    this.matrix = new Matrix4().setTranslate(px, py, pz)
        .multiply(this.rotation).scale(sx, sy, sz);
  }
  return this.matrix;
};

/**
 * Adds the given CS336Object to this object's list of children.
 * @param child
 */
CS336Object.prototype.addChild = function(child)
{
  this.children.push(child);
};

/**
 * Renders this object using the drawObject callback function and recursing 
 * through the children.
 * @param matrixWorld 
 *   frame transformation for this object's parent
 */
CS336Object.prototype.render = function(matrixWorld)
{
  // clone and update the world matrix
  var currentWorld = new Matrix4(matrixWorld).multiply(this.getMatrix());

  // invoke callback
  this.drawObject(currentWorld);

  // recurse through children using current world matrix
  for (var i = 0; i < this.children.length; ++i)
  {
    this.children[i].render(currentWorld);
  }
};

/**
 * Moves the CS336Object along its negative z-axis by the given amount.
 */
CS336Object.prototype.moveForward = function(distance)
{
  // TODO
};

/**
 * Moves the CS336Object along its positive z-axis by the given amount.
 */
CS336Object.prototype.moveBack = function(distance)
{
  this.moveForward(-distance);
};

/**
 * Moves the CS336Object along its positive x-axis by the given amount.
 */
CS336Object.prototype.moveRight = function(distance)
{
  // TODO
};

/**
 * Moves the CS336Object along its negative x-axis by the given amount.
 */
CS336Object.prototype.moveLeft = function(distance)
{
  this.moveRight(-distance);
};

/**
 * Moves the CS336Object along its own y-axis by the given amount.
 */
CS336Object.prototype.moveUp = function(distance)
{
  // TODO
};

/**
 * Moves the CS336Object along its own negative y-axis by the given amount.
 */
CS336Object.prototype.moveDown = function(distance)
{
  this.moveUp(-distance);
};

/**
 * Rotates the CS336Object ccw about its x-axis.
 */
CS336Object.prototype.rotateX = function(degrees)
{
  // TODO
};

/**
 * Rotates the CS336Object ccw about its y-axis.
 */
CS336Object.prototype.rotateY = function(degrees)
{
  // TODO
};

/**
 * Rotates the CS336Object ccw about its z-axis.
 */
CS336Object.prototype.rotateZ = function(degrees, x, y, z)
{
  // TODO
};

/**
 * Rotates the CS336Object ccw about the given axis, specified as a vector.
 */
CS336Object.prototype.rotateOnAxis = function(degrees, x, y, z)
{
  // TODO
};

/**
 * Rotates the CS336Object ccw about the given axis, specified in terms of
 * pitch and head angles (as in spherical coordinates).
 */
CS336Object.prototype.rotateOnAxisEuler = function(degrees, pitch, head)
{
  // TODO
};

/**
 * Rotates the CS336Object counterclockwise about an axis through its center that is
 * parallel to the vector (0, 1, 0).
 */
CS336Object.prototype.turnLeft = function(degrees)
{
  // TODO
};

/**
 * Rotates the CS336Object clockwise about an axis through its center that is
 * parallel to the vector (0, 1, 0).
 */
CS336Object.prototype.turnRight = function(degrees)
{
  this.turnLeft(-degrees);
};

/**
 * Moves the CS336Object the given number of degrees along a great circle. The axis
 * of rotation is parallel to the CS336Object's x-axis and intersects the CS336Object's
 * positive z-axis the given distance in front of the CS336Object. (This operation is
 * equivalent to a moveForward, lookDown and then moveBack.
 */
CS336Object.prototype.orbitUp = function(degrees, distance)
{
  // TODO
};

/**
 * Moves the CS336Object the given number of degrees along a great circle. The axis
 * of rotation is parallel to the CS336Object's x-axis and intersects the CS336Object's
 * positive z-axis the given distance in front of the CS336Object. (This operation is
 * equivalent to a moveForward, lookUp and then moveBack.
 */
CS336Object.prototype.orbitDown = function(degrees, distance)
{
  this.orbitUp(-degrees, distance);
};

/**
 * Moves the CS336Object the given number of degrees around a circle of latitude. The
 * axis of rotation is parallel to the world up vector and intersects the
 * CS336Object's positive z-axis the given distance in front of the CS336Object. (This
 * operation is equivalent to a moveForward, turnLeft, and moveBack.)
 */
CS336Object.prototype.orbitRight = function(degrees, distance)
{
  // TODO
};

/**
 * Moves the CS336Object the given number of degrees around a circle of latitude. The
 * axis of rotation is parallel to the world up vector and intersects the
 * CS336Object's positive z-axis the given distance in front of the CS336Object. (This
 * operation is equivalent to a moveForward, turnRight, and moveBack.)
 */
CS336Object.prototype.orbitLeft = function(degrees, distance)
{
  this.orbitRight(-degrees, distance);
};

/**
 * Orients the CS336Object at its current location to face the given position,
 * i.e., its positive z-axis is aligned with the given position.
 */
CS336Object.prototype.lookAt = function(x, y, z)
{
  // TODO
};
