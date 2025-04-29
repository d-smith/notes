from threading import Lock
from typing import Dict

# Custom Exceptions
class InventoryError(Exception):
    """Base class for inventory-related exceptions."""
    pass

class NotEnoughStockError(InventoryError):
    """Raised when there's not enough stock to perform the remove operation."""
    pass

class ItemNotFoundError(InventoryError):
    """Raised when the specified item_id is not found in the inventory."""
    pass

class InventoryManager:
    """
    A class to manage inventory items.

    Attributes:
        inventory (Dict[str, int]): A dictionary mapping item_id to available quantity.
        lock (Lock): A threading lock for ensuring thread-safe operations.
    """

    def __init__(self):
        self.inventory: Dict[str, int] = {}
        self.lock = Lock()  # Used for thread safety if needed

    def add_item(self, item_id: str, quantity: int) -> None:
        """
        Add a specified quantity of an item to the inventory.
        
        Args:
            item_id (str): Unique identifier for the item.
            quantity (int): The amount to add (must be a positive integer).

        Raises:
            TypeError: If item_id is not a string.
            ValueError: If quantity is not a positive integer.
        """
        if not isinstance(item_id, str):
            raise TypeError("item_id must be a string")
        if not isinstance(quantity, int) or quantity <= 0:
            raise ValueError("quantity must be a positive integer")
        
        # Ensure thread-safe updates
        with self.lock:
            self.inventory[item_id] = self.inventory.get(item_id, 0) + quantity

    def remove_item(self, item_id: str, quantity: int) -> None:
        """
        Remove a specified quantity of an item from the inventory.
        
        Args:
            item_id (str): Unique identifier for the item.
            quantity (int): The amount to remove (must be a positive integer).

        Raises:
            TypeError: If item_id is not a string.
            ValueError: If quantity is not a positive integer.
            ItemNotFoundError: When the item_id is not present.
            NotEnoughStockError: When there isn't enough stock to remove the specified quantity.
        """
        if not isinstance(item_id, str):
            raise TypeError("item_id must be a string")
        if not isinstance(quantity, int) or quantity <= 0:
            raise ValueError("quantity must be a positive integer")
        
        # Ensure thread-safe updates
        with self.lock:
            if item_id not in self.inventory:
                raise ItemNotFoundError(f"Item '{item_id}' not found in inventory")
            if self.inventory[item_id] < quantity:
                raise NotEnoughStockError(
                    f"Insufficient stock to remove: available {self.inventory[item_id]}, required {quantity}"
                )
            self.inventory[item_id] -= quantity

            # Optionally remove the item entirely if the quantity reaches 0
            if self.inventory[item_id] == 0:
                del self.inventory[item_id]

    def check_stock(self, item_id: str) -> int:
        """
        Check and return the available stock for a given item.

        Args:
            item_id (str): Unique identifier for the item.

        Returns:
            int: The available quantity (returns 0 if the item is not found).

        Raises:
            TypeError: If item_id is not a string.
        """
        if not isinstance(item_id, str):
            raise TypeError("item_id must be a string")

        # Locking for consistency even though a read-only operation generally might be OK
        with self.lock:
            return self.inventory.get(item_id, 0)
